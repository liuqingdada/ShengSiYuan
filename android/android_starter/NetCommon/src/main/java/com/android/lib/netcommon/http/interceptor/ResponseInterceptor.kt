package com.android.lib.netcommon.http.interceptor

import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.internal.http.promisesBody
import okio.Buffer
import okio.GzipSource
import java.io.EOFException
import java.io.IOException
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

/**
 * Created by cooper
 * 21-4-29.
 * Email: 1239604859@qq.com
 */
abstract class ResponseInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response: Response
        try {
            response = chain.proceed(request)
        } catch (e: IOException) {
            responseErrorFormat(e.toString())
            throw e
        }

        val responseBody = response.body!!
        val contentLength = responseBody.contentLength()
        val headers = response.headers
        if (!response.promisesBody()) {
            responseErrorFormat("END HTTP")
        } else if (bodyHasUnknownEncoding(headers)) {
            responseErrorFormat("END HTTP (encoded body omitted)")
        } else {
            val source = responseBody.source()
            source.request(Long.MAX_VALUE) // Buffer the entire body.
            var buffer = source.buffer

            if ("gzip".equals(headers["Content-Encoding"], ignoreCase = true)) {
                GzipSource(buffer.clone()).use { gzippedResponseBody ->
                    buffer = Buffer()
                    buffer.writeAll(gzippedResponseBody)
                }
            }

            val contentType = responseBody.contentType()
            val charset: Charset =
                contentType?.charset(StandardCharsets.UTF_8) ?: StandardCharsets.UTF_8

            if (!buffer.isProbablyUtf8()) {
                responseErrorFormat("END HTTP (binary ${buffer.size}-byte body omitted)")
                return response
            }

            if (contentLength != 0L) {
                return responseFormat(response, buffer.clone().readString(charset))
            }
        }
        return response
    }

    abstract fun responseFormat(response: Response, body: String) : Response

    abstract fun responseErrorFormat(error: String)

    private fun bodyHasUnknownEncoding(headers: Headers): Boolean {
        val contentEncoding = headers["Content-Encoding"] ?: return false
        return !contentEncoding.equals("identity", ignoreCase = true) &&
                !contentEncoding.equals("gzip", ignoreCase = true)
    }
}

/**
 * Returns true if the body in question probably contains human readable text. Uses a small
 * sample of code points to detect unicode control characters commonly used in binary file
 * signatures.
 */
internal fun Buffer.isProbablyUtf8(): Boolean {
    try {
        val prefix = Buffer()
        val byteCount = size.coerceAtMost(64)
        copyTo(prefix, 0, byteCount)
        for (i in 0 until 16) {
            if (prefix.exhausted()) {
                break
            }
            val codePoint = prefix.readUtf8CodePoint()
            if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                return false
            }
        }
        return true
    } catch (_: EOFException) {
        return false // Truncated UTF-8 sequence.
    }
}
