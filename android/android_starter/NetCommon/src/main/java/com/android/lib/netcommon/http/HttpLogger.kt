package com.android.lib.netcommon.http

import com.android.common.utils.LogUtil
import okhttp3.logging.HttpLoggingInterceptor

/**
 * Created by cooper
 * 21-4-13.
 * Email: 1239604859@qq.com
 */
class HttpLogger : HttpLoggingInterceptor.Logger {
    companion object {
        private const val TAG = "HttpLogger"
    }

    override fun log(message: String) {
        LogUtil.d(TAG, message)
    }
}