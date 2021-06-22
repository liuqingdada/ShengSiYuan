package com.android.lib.netcommon.http

import com.android.common.utils.LogUtil
import com.android.lib.netcommon.gson.GsonUtils
import com.android.lib.netcommon.http.converter.GsonConverterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.ConcurrentHashMap

/**
 * Created by cooper
 * 21-4-13.
 * Email: 1239604859@qq.com
 */
object HttpService {
    private lateinit var okHttpClient: OkHttpClient

    /**
     * key: baseUrl
     */
    private val retrofits by lazy {
        ConcurrentHashMap<String, Retrofit>()
    }

    fun init(vararg interceptors: Interceptor) {
        val builder = OkHttpClient.Builder()
        interceptors.forEach {
            builder.addInterceptor(it)
        }
        // addLast logging:
        if (LogUtil.isDebug()) {
            val loggingInterceptor = HttpLoggingInterceptor(HttpLogger())
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            builder.addInterceptor(loggingInterceptor)
        }
        //val context = ApplicationUtils.getApplication()
        okHttpClient = builder
            // 暂时没必要都存
            //.cookieJar(CookieJarImpl(PersistentCookieStore(context)))
            .build()
    }

    fun getClient(vararg interceptors: Interceptor): OkHttpClient {
        val builder = okHttpClient.newBuilder()
        interceptors.forEach {
            builder.addInterceptor(it)
        }
        return builder.build()
    }

    @JvmOverloads
    fun <T> getService(baseUrl: String, api: Class<T>, client: OkHttpClient = okHttpClient): T {
        retrofits[baseUrl]?.let {
            return it.create(api)
        }
        val retrofit = Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonUtils.getGson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(baseUrl)
            .build()
        retrofits[baseUrl] = retrofit
        return retrofit.create(api)
    }
}