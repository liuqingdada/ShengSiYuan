## **OkHttp 源码剖析**

大家好，我是Cooper，一名热爱技术的 Android 开发，本文宗旨在于帮助大家快速梳理OkHttp的源码流程， **本文基于okhttp-4.9.0**

### **0x01 OkHttpClient**

解释OkHttpClient之前，我们先了解下 Call 的定义：

```kotlin
interface Call : Cloneable {
    fun interface Factory { 
        fun newCall(request: Request): Call
    }
}
```

fun interface 是kotlin 1.4 新加的函数式接口, OkHttpClient 实现了此接口

Call 是一个**已经准备好执行**的请求，可以取消，因为这个对象表示单个请求或者响应对（流），因此无法执行两次

OkHttpClient其实就是Call的工厂，它可以用来发送HTTP请求和读取其响应

注意，OkHttpClients应该被共享，原因如下：

当你创建单个OkHttpClient实例并将其用于所有HTTP调用时，OkHttp的性能最佳。这是因为每个客户端都拥有自己的连接池和线程池。复用连接和线程可减少延迟并节省内存。相反，为每个请求创建客户端都会浪费空闲池上的资源

另外，通过 newBuilder() 方法可以自定义共享的OkHttpClient实例，这样可以构建共享相同连接池，线程池和配置的客户端。使用此方法可以为特定目的配置派生的客户端

Shutdown 不是必要的

如果保留的线程和连接保持空闲状态，他们会自动释放。但是如果应用程序需要主动释放资源，那么可以如下做：

```kotlin
client.dispatcher().executorService().shutdown()

client.connectionPool().evictAll()

client.cache().close()
```

OkHttp还使用守护程序线程进行HTTP / 2连接。 如果它们保持空闲状态，它们将自动退出。

```kotlin
/* Builder 主要源码 */
class Builder constructor() {
    internal var dispatcher: Dispatcher = Dispatcher()
    internal var connectionPool: ConnectionPool = ConnectionPool()
    internal val interceptors: MutableList<Interceptor> = mutableListOf()
    internal val networkInterceptors: MutableList<Interceptor> = mutableListOf()
    internal var eventListenerFactory: EventListener.Factory = EventListener.NONE.asFactory()
    internal var retryOnConnectionFailure = true
    internal var authenticator: Authenticator = Authenticator.NONE
    internal var followRedirects = true
    internal var followSslRedirects = true
    internal var cookieJar: CookieJar = CookieJar.NO_COOKIES
    internal var cache: Cache? = null
    internal var dns: Dns = Dns.SYSTEM
    internal var proxy: Proxy? = null
    internal var proxySelector: ProxySelector? = null
    internal var proxyAuthenticator: Authenticator = Authenticator.NONE
    internal var socketFactory: SocketFactory = SocketFactory.getDefault()
    internal var sslSocketFactoryOrNull: SSLSocketFactory? = null
    internal var x509TrustManagerOrNull: X509TrustManager? = null
    internal var connectionSpecs: List<ConnectionSpec> = DEFAULT_CONNECTION_SPECS
    internal var protocols: List<Protocol> = DEFAULT_PROTOCOLS
    internal var hostnameVerifier: HostnameVerifier = OkHostnameVerifier
    internal var certificatePinner: CertificatePinner = CertificatePinner.DEFAULT
    internal var certificateChainCleaner: CertificateChainCleaner? = null
    internal var callTimeout = 0
    internal var connectTimeout = 10_000
    internal var readTimeout = 10_000
    internal var writeTimeout = 10_000
    internal var pingInterval = 0
    internal var minWebSocketMessageToCompress = RealWebSocket.DEFAULT_MINIMUM_DEFLATE_SIZE
    internal var routeDatabase: RouteDatabase? = null
    // ...
}
```

源码中使用Builder设计模式构建OkHttpClient对象，所以这些成员，OkHttpClient也是一一对应的，这些组件下文中会找几个重要的展开分析。



**0x02 从newCall出发**

```kotlin
/** Prepares the [request] to be executed at some point in the future. */
override fun newCall(request: Request): Call = RealCall(this, request, forWebSocket = false)
```

Request 比较简单，主要包括 url，method，headers，body的定义

重点分析一下RealCall：

```kotlin
class RealCall(
  val client: OkHttpClient,
  /** 
   * The application's original request unadulterated by redirects or auth headers. 
   * 应用程序的原始请求不受重定向或auth标头的影响
   * 一般情况下，就是我们上面说的Request对象
   */
  val originalRequest: Request,
  val forWebSocket: Boolean
) : Call {
    private val connectionPool: RealConnectionPool = client.connectionPool.delegate
    // ...
}
```

之前我们已经说过Call的作用了，RealCall也是Call的唯一实现

RealCall是OkHttp的应用程序和网络层之间的桥梁。RealCall暴露了高级应用程序层的原始组成：连接，请求，响应和流

RealCall支持异步取消，如果HTTP/2处于活动状态，则取消操作将取消该流，但不会取消共享其连接的其他流。 但是，如果TLS握手仍在进行中，则取消操作可能会中断整个连接。

超时处理：

```kotlin
private val timeout = object : AsyncTimeout() {
    override fun timedOut() {
      cancel()
    }
  }.apply {
    timeout(client.callTimeoutMillis.toLong(), MILLISECONDS)
  }

 /**
  * 等待最多timeout时间，然后中止操作。 使用每个操作超时意味着只要向前取得进展，操作序列就不会失败。
  * 如果timeout == 0 ，则操作将无限期运行。 （操作系统超时可能仍然适用)
  */
  open fun timeout(timeout: Long, unit: TimeUnit): Timeout {
    require(timeout >= 0) { "timeout < 0: $timeout" }
    timeoutNanos = unit.toNanos(timeout)
    return this
  }
```

发起请求的入口：

```kotlin
override fun execute(): Response {
    check(executed.compareAndSet(false, true)) { "Already Executed" }

    timeout.enter()
    callStart()
    try {
      client.dispatcher.executed(this)
      return getResponseWithInterceptorChain()
    } finally {
      client.dispatcher.finished(this)
    }
  }

  override fun enqueue(responseCallback: Callback) {
    check(executed.compareAndSet(false, true)) { "Already Executed" }

    callStart()
    client.dispatcher.enqueue(AsyncCall(responseCallback))
  }
```

首先，回过头，看一下Client中的dispatcher：

```kotlin
class Dispatcher constructor() {
  // 同时执行的最大请求数
  @get:Synchronized var maxRequests = 64

  //每个主机要同时执行的最大请求数。 这通过URL的主机名限制了请求。 请注意，对单个IP地址的并发请求可能仍会超出此限制：多个主机名可能共享一个IP地址或通过同一HTTP代理路由
  @get:Synchronized var maxRequestsPerHost = 5

  //每次调度程序空闲时（运行的调用数返回零时）将调用的回调
  @set:Synchronized
  @get:Synchronized
  var idleCallback: Runnable? = null

  private var executorServiceOrNull: ExecutorService? = null

  @get:Synchronized
  @get:JvmName("executorService") val executorService: ExecutorService
    get() {
      if (executorServiceOrNull == null) {
        executorServiceOrNull = ThreadPoolExecutor(0, Int.MAX_VALUE, 60, TimeUnit.SECONDS,
            SynchronousQueue(), threadFactory("$okHttpName Dispatcher", false))
      }
      return executorServiceOrNull!!
    }

  /** Ready async calls in the order they'll be run. */
  private val readyAsyncCalls = ArrayDeque<AsyncCall>()

  /** Running asynchronous calls. Includes canceled calls that haven't finished yet. */
  private val runningAsyncCalls = ArrayDeque<AsyncCall>()

  /** Running synchronous calls. Includes canceled calls that haven't finished yet. */
  private val runningSyncCalls = ArrayDeque<RealCall>()

  constructor(executorService: ExecutorService) : this() {
    this.executorServiceOrNull = executorService
  }

  internal fun enqueue(call: AsyncCall) {
    synchronized(this) {
      readyAsyncCalls.add(call)
      if (!call.call.forWebSocket) {
        val existingCall = findExistingCallWithHost(call.host)
        if (existingCall != null) call.reuseCallsPerHostFrom(existingCall)
      }
    }
    promoteAndExecute()
  }

  /** Used by [Call.execute] to signal it is in-flight. */
  @Synchronized internal fun executed(call: RealCall) {
    runningSyncCalls.add(call)
  }
}
```

对于同步请求的情况，直接就是把RealCall对象加到runningSyncCalls中，然后执行getResponseWithInterceptorChain()，这个方法直接返回的就是Response对象，并且执行一系列的拦截器，最后调用dispatcher的finish方法，移除RealCall对象。

```kotlin
@Throws(IOException::class)
  internal fun getResponseWithInterceptorChain(): Response {
    // Build a full stack of interceptors.
    val interceptors = mutableListOf<Interceptor>()
    interceptors += client.interceptors
    interceptors += RetryAndFollowUpInterceptor(client)
    interceptors += BridgeInterceptor(client.cookieJar)
    interceptors += CacheInterceptor(client.cache)
    interceptors += ConnectInterceptor
    if (!forWebSocket) {
      interceptors += client.networkInterceptors
    }
    interceptors += CallServerInterceptor(forWebSocket)

    val chain = RealInterceptorChain(
        call = this,
        interceptors = interceptors,
        index = 0,
        exchange = null,
        request = originalRequest,
        connectTimeoutMillis = client.connectTimeoutMillis,
        readTimeoutMillis = client.readTimeoutMillis,
        writeTimeoutMillis = client.writeTimeoutMillis
    )

    try {
      val response = chain.proceed(originalRequest)
      if (isCanceled()) {
        response.closeQuietly()
        throw IOException("Canceled")
      }
      return response
    } catch (e: IOException) {
    } finally {
    }
  }
```

我们可以看到，这里是RealChain调用proceed的入口，并且如果cancel的话，抛出IO异常

对于拦截器的说明：

```kotlin
fun interface Interceptor {
  @Throws(IOException::class)
  fun intercept(chain: Chain): Response

  interface Chain {
      // ...
  }
}
```

简单说，拦截器是观察，修改并可能使发出的请求和相应的请求短路返回。该接口的实现抛出[IOException]以表示连接失败。

interface Chain 的唯一实现是RealInterceptorChain，这里是拦截器调用的关键入口，重点分析下proceed方法：

```kotlin
@Throws(IOException::class)
  override fun proceed(request: Request): Response {
    calls++

    // Call the next interceptor in the chain.
    val next = copy(index = index + 1, request = request)
    val interceptor = interceptors[index]

    @Suppress("USELESS_ELVIS")
    val response = interceptor.intercept(next) ?: throw NullPointerException(
        "interceptor $interceptor returned null")
    return response
  }
```

我们只看check除外的代码，其实就是每次执行proceed后，就从interceptors中拿下一个拦截器，并调用intercept方法

至此我们知道，Client中的我们自定义的拦截器会先调用，这也就是为什么，我们一定会在自定义拦截器中调用proceed的原因，那么方法返回呢，其实顺序正好反过来，按照源码顺序，当我们自定义的最后一个拦截器走完后：

RetryAndFollowUpInterceptor：此拦截器从故障中恢复，并根据需要进行重定向。

BridgeInterceptor：从应用程序代码到网络代码的桥梁。 首先，它根据用户请求构建网络请求。 然后，它继续呼叫网络。 最后，它根据网络响应建立用户响应。

CacheInterceptor：从缓存中获取服务器请求数据，和将响应写入缓存的功能

ConnectInterceptor：打开与目标服务器的连接，然后进入下一个拦截器。 该网络可能用于返回的响应，或者用于使用条件GET验证缓存的响应。

```kotlin
object ConnectInterceptor : Interceptor {
  @Throws(IOException::class)
  override fun intercept(chain: Interceptor.Chain): Response {
    val realChain = chain as RealInterceptorChain
    val exchange = realChain.call.initExchange(chain)
    val connectedChain = realChain.copy(exchange = exchange)
    return connectedChain.proceed(realChain.request)
  }
}
```

其实到这里，已经是最后一个拦截器了，proceed方法会直接返回Response，然后从这里向上，把Response对象逐一的返回给CacheInterceptor，BridgeInterceptor，RetryAndFollowUpInterceptor 和我们自定义的拦截器。这里我们重点看下initExchange方法：

```kotlin
internal fun initExchange(chain: RealInterceptorChain): Exchange {
    val exchangeFinder = this.exchangeFinder!!
    val codec = exchangeFinder.find(client, chain)
    val result = Exchange(this, eventListener, exchangeFinder, codec)
    return result
  }
```

我们跟进一下exchangeFinder.find(client, chain)：

```kotlin
fun find(
    client: OkHttpClient,
    chain: RealInterceptorChain
  ): ExchangeCodec {
    try {
      val resultConnection = findHealthyConnection(
          connectTimeout = chain.connectTimeoutMillis,
          readTimeout = chain.readTimeoutMillis,
          writeTimeout = chain.writeTimeoutMillis,
          pingIntervalMillis = client.pingIntervalMillis,
          connectionRetryEnabled = client.retryOnConnectionFailure,
          doExtensiveHealthChecks = chain.request.method != "GET"
      )
      return resultConnection.newCodec(client, chain)
    } catch (e: RouteException) {
    }
  }
```

继续跟进：

```kotlin
@Throws(SocketException::class)
  internal fun newCodec(client: OkHttpClient, chain: RealInterceptorChain): ExchangeCodec {
    val socket = this.socket!!
    val source = this.source!!
    val sink = this.sink!!
    val http2Connection = this.http2Connection

    return if (http2Connection != null) {
      Http2ExchangeCodec(client, this, chain, http2Connection)
    } else {
      socket.soTimeout = chain.readTimeoutMillis()
      source.timeout().timeout(chain.readTimeoutMillis.toLong(), MILLISECONDS)
      sink.timeout().timeout(chain.writeTimeoutMillis.toLong(), MILLISECONDS)
      Http1ExchangeCodec(client, this, source, sink)
    }
  }
```

到这里已经真相大白了，最终http的网络实现就是Http2ExchangeCodec或者Http1ExchangeCodec

大家如果认真思考，我们是否有遗漏的地方？没错，就是OkHttp的连接复用机制，我们回头看下源码，我们讲dispatcher的时候，紧挨着的那个成员，就是ConnectionPool：

```kotlin
class ConnectionPool internal constructor(
  internal val delegate: RealConnectionPool
) {
  constructor(
    maxIdleConnections: Int,
    keepAliveDuration: Long,
    timeUnit: TimeUnit
  ) : this(RealConnectionPool(
      taskRunner = TaskRunner.INSTANCE,
      maxIdleConnections = maxIdleConnections,
      keepAliveDuration = keepAliveDuration,
      timeUnit = timeUnit
  ))

  constructor() : this(5, 5, TimeUnit.MINUTES)

  /** Returns the number of idle connections in the pool. */
  fun idleConnectionCount(): Int = delegate.idleConnectionCount()

  /** Returns total number of connections in the pool. */
  fun connectionCount(): Int = delegate.connectionCount()

  /** Close and remove all idle connections in the pool. */
  fun evictAll() {
    delegate.evictAll()
  }
}
```

管理HTTP和HTTP / 2连接的重用，以减少网络延迟。 共享相同地址的HTTP请求可以共享一个Connection 。 此类实现了将哪些连接保持打开状态以备将来使用的策略。

**注意：我们看构造方法的默认参数，官方有如下解释：使用适合于单用户应用程序的调整参数创建一个新的连接池。此池中的调整参数可能会在将来的OkHttp版本中更改。当前，该池最多可容纳5个空闲连接，这些空闲连接在闲置5分钟后将被驱逐。**

然后，我们不难发现，构造方法最终其实构建了RealConnectionPool，也就是delegate对象，OK，回头看RealCall的代码，其中第一个成员变量就是 connectionPool，而且就是这个delegate，RealConnectionPool的代码我们暂且不去展开了，主要有如下几个方法：

```kotlin
fun put(connection: RealConnection) {
    connection.assertThreadHoldsLock()

    connections.add(connection)
    cleanupQueue.schedule(cleanupTask)
}

fun evictAll() {
}

fun cleanup(now: Long): Long {
}
```

其实，我们可以大胆猜测了，还记得创建HttpExchangeCodec的地方吧，应该就在那里调用的put，把连接加进来进行维护吧。我们回头看一下exchangeFinder.find(client, chain)这个方法，在newCodec之前，有一个findHealthyConnection，哈哈，“大白话就是找个身体好点的连接啊！！”：

```kotlin
/**
 * 查找连接，如果连接状况良好，则将其返回。 如果不健康，请重复此过程，直到找到健康的连接为止。
 */
@Throws(IOException::class)
  private fun findHealthyConnection(
    connectTimeout: Int,
    readTimeout: Int,
    writeTimeout: Int,
    pingIntervalMillis: Int,
    connectionRetryEnabled: Boolean,
    doExtensiveHealthChecks: Boolean
  ): RealConnection {
    while (true) {
      val candidate = findConnection(
          connectTimeout = connectTimeout,
          readTimeout = readTimeout,
          writeTimeout = writeTimeout,
          pingIntervalMillis = pingIntervalMillis,
          connectionRetryEnabled = connectionRetryEnabled
      )
      // Confirm that the connection is good.
      if (candidate.isHealthy(doExtensiveHealthChecks)) {
        return candidate
      }
      // ...
      throw IOException("exhausted all routes")
    }
  }
```

我们继续跟一下 findConnection：

```kotlin
  /**
   * 返回用于托管新流的连接。如果存在现有连接，则首选现有连接，然后是池，最后建立一个新连接。
   * 这将在每次阻止操作之前检查取消。
   */
  @Throws(IOException::class)
  private fun findConnection(
    connectTimeout: Int,
    readTimeout: Int,
    writeTimeout: Int,
    pingIntervalMillis: Int,
    connectionRetryEnabled: Boolean
  ): RealConnection {
    //...
    if (connectionPool.callAcquirePooledConnection(address, call, null, false)) {
      val result = call.connection!!
      eventListener.connectionAcquired(call, result)
      return result
    }
    // ...

    // Connect. Tell the call about the connecting call so async cancels work.
    val newConnection = RealConnection(connectionPool, route)
    call.connectionToCancel = newConnection
    try {
      newConnection.connect(
          connectTimeout,
          readTimeout,
          writeTimeout,
          pingIntervalMillis,
          connectionRetryEnabled,
          call,
          eventListener
      )
    } finally {
      call.connectionToCancel = null
    }
    call.client.routeDatabase.connected(newConnection.route())

    synchronized(newConnection) {
      connectionPool.put(newConnection)             // put 到连接池
      call.acquireConnectionNoEvents(newConnection)
    }
    return newConnection
  }
```

同步网络请求到此为止，下面我们回过头来，看一下异步的网络请求：



**0x03 梦回newCall**

我们回头看RealCall的enqueue方法，其实是把AsyncCall对象添加给Dispatcher组件，回头去看Dispatcher的源码，我们发现把AsyncCall添加到readyAsyncCalls，然后执行promoteAndExecute方法：

```kotlin
private fun promoteAndExecute(): Boolean {
    this.assertThreadDoesntHoldLock()
    // ... ...
    for (i in 0 until executableCalls.size) {
      val asyncCall = executableCalls[i]
      asyncCall.executeOn(executorService)
    }
    return isRunning
  }
```

executorService对象我们再看Dispatcher组件的时候应该注意到了吧，是一个自定义的线程池，我们跟一下executeOn方法：

```kotlin
fun executeOn(executorService: ExecutorService) {
      client.dispatcher.assertThreadDoesntHoldLock()
      var success = false
      try {
        executorService.execute(this)  // AsyncCall 的 run 会被调用
        success = true
      } catch (e: RejectedExecutionException) {
        val ioException = InterruptedIOException("executor rejected")
        ioException.initCause(e)
        noMoreExchanges(ioException)
        responseCallback.onFailure(this@RealCall, ioException)
      } finally {
        if (!success) {
          client.dispatcher.finished(this) // This call is no longer running!
        }
      }
    }
```

所以最终线程池会调用AsyncCall的run方法：

```kotlin
override fun run() {
      threadName("OkHttp ${redactedUrl()}") {
        var signalledCallback = false
        timeout.enter()
        try {
          val response = getResponseWithInterceptorChain()
          signalledCallback = true
          responseCallback.onResponse(this@RealCall, response)
        } // ...
      }
    }
  }
```

所以，流程又来到了getResponseWithInterceptorChain()。




