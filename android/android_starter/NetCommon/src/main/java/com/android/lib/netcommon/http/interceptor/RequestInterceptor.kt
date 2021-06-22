package com.android.lib.netcommon.http.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

/**
 * Created by cooper
 * 2021/5/6.
 * Email: 1239604859@qq.com
 */
abstract class RequestInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return chain.proceed(signHeader(request))
    }

    protected abstract fun signHeader(request: Request): Request
}