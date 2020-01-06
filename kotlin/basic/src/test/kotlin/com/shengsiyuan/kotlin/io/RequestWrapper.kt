
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.util.concurrent.TimeUnit
import kotlin.test.Test

class RequestWrapper {
    var url: String? = null
    var method: String? = null
    var body: RequestBody? = null
    var timeout: Long = 10
    internal var _success: (String) -> Unit = { }
    internal var _fail: (Throwable) -> Unit = {}
    fun onSuccess(onSuccess: (String) -> Unit) {
        _success = onSuccess
    }

    fun onFail(onError: (Throwable) -> Unit) {
        _fail = onError
    }

    fun http(init: RequestWrapper.() -> Unit) {
        val wrap = RequestWrapper()
        wrap.init()
        executeForResult(wrap)
    }

    private fun executeForResult(wrap: RequestWrapper) {
        Flowable.create<Response>({ e ->
            onExecute(wrap)?.let { e.onNext(it) }
        }, BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { resp ->
                            wrap._success(resp.body!!.string())
                        },
                        { e -> wrap._fail(e) })
    }

    private fun onExecute(wrap: RequestWrapper): Response? {
        var req: Request? = null
        when (wrap.method) {
            "get", "Get", "GET" -> req = wrap.url?.let { Request.Builder().url(it).build() }
            "post", "Post", "POST" -> req = wrap.url?.let { wrap.body?.let { it1 -> Request.Builder().url(it).post(it1).build() } }
            "put", "Put", "PUT" -> req = wrap.url?.let { wrap.body?.let { it1 -> Request.Builder().url(it).put(it1).build() } }
            "delete", "Delete", "DELETE" -> req = wrap.url?.let { Request.Builder().url(it).delete(wrap.body).build() }
        }
        val http = OkHttpClient.Builder().connectTimeout(wrap.timeout, TimeUnit.SECONDS).build()
        val resp = req?.let { http.newCall(it).execute() }
        return resp
    }

    @Test
    fun get() {
        http {
            url = "http://www.163.com/"
            method = "get"
            onSuccess { string ->
                println(string)
            }
            onFail { e ->
                println(e.message)
            }
        }
    }
}