package com.shengsiyuan.coroutine.day03

import kotlinx.coroutines.*
import java.util.concurrent.Executors

/**
 * Created by liuqing.yang
 * 2020/3/27.
 * Email: 1239604859@qq.com
 *
 * 协程与线程之间的关系
 * 协程上下文与分发器(Coroutine Context与Dispatcher)
 * 协程总是会在某个上下文中执行, 这个上下文实际上是由CoroutineContext类型的一个实例来表示的,
 * 该实例是由Kotlin标准来定义的
 *
 * 协程上下文本质上是各种元素所构成的一个集合, 其中, 主要的元素包括协程的Job, 以及分发器
 *
 * 所谓的分发器, 其实主要功能就是确定由哪个线程来执行我们所指定的协程代码
 *
 * 协程上下文包含了一个协程分发器(CoroutineDispatcher), 协程分发器确定了到底由哪个线程或是线程池来执行
 * 我们所指定的协程; 协程分发器可以将协程的执行限制到一个具体指定的线程, 也可以将协程的执行分发到一个线程池中,
 * 由线程池中的某个线程来执行我们所指定的协程, 还可以不加任何限制地去执行我们所指定的协程代码(在这种情况下,
 * 我们所指定的协程代码到底是由哪个线程或线程池来执行的是不确定的, 它需要根据程序的实际执行情况方能确定, 这种
 * 方式的协程分发器在一般的开发中使用较少, 它只用在一些极为特殊的情况下)
 *
 * 所有的协程构建器(Coroutine Builder)如launch何async都会接收一个可选的CoroutineContext参数,
 * 该参数可用于显示指定新协程所运行的分发器以及其他上下文元素
 *
 *
 * 程序分析：
 * 1. 当通过launch来启动协程且不指定协程分发器时，它会继承启动它的那个CoroutineScope的上下文与分发器，对该示例
 *    来说，它会继承runBlocking的上下文，而runBlocking则是运行在main线程当中
 * 2. Dispatchers.Unconfined 是一种很特殊的协程分发器，它在该示例中也是运行咋main线程中，但实际上，其运行机制
 *    与不指定协程分发器时时完全不同的
 * 3. Dispatchers.Default是默认的分发器，当协程是通多GlobalScope来启动的时候，它会使用该默认的分发器来启动协程，
 *    它会使用一个后台的共享线程池来运行我们的协程代码。因此，launch(Dispatchers.Default)等价于(这里只说线程, 作用域是不同的)
 *    GlobalScope.launch { }
 * 4. asCoroutineDispatcher Kotlin提供的扩展方法，使得线程池来执行我们所指定的协程代码。在实际开法中，使用专门
 *    的线程池来执行协程代码代价是非常高的，因此在协程代码执行完毕后，我们必须要释放相应的资源，这里就需要使用close
 *    方法来关闭相应的协程分发器，从而释放资源；也可以将该协程分发器存储到一个顶层变量中，以便在程序的其他地方进行复用
 */

fun main() = runBlocking<Unit> {
    launch {
        println("no param, thread: ${Thread.currentThread().name}")
    }

    launch(Dispatchers.Unconfined) {
        // delay(100) // 加上延迟，就会发现不是运行在main线程了
        println("dispatchers unconfined, thread: ${Thread.currentThread().name}")
    }

    launch(Dispatchers.Default) {
        println("dispachers default, thread: ${Thread.currentThread().name}")
    }

//    newFixedThreadPoolContext()
//    newSingleThreadContext()
    val dispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
    launch(dispatcher) {
        println("single thread executor service, thread: ${Thread.currentThread().name}")
        dispatcher.close()
    }

    GlobalScope.launch {
        println("globle scope launch, thread: ${Thread.currentThread().name}")
    }
}
