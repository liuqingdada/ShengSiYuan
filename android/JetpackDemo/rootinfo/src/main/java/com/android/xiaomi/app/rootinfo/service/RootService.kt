package com.android.xiaomi.app.rootinfo.service

import android.os.Handler
import android.os.HandlerThread
import com.android.app.common.utils.ApplicationUtils
import com.android.app.common.utils.LogUtil
import com.android.xiaomi.app.rootinfo.location.ILocationManager
import com.android.xiaomi.app.rootinfo.location.LocationInfo
import com.android.xiaomi.app.rootinfo.location.LocationManager

/**
 * Created by cooper
 * 20-6-17.
 * Email: 1239604859@qq.com
 */
class RootService {
    companion object {
        private const val TAG = "RootService"
    }

    private val context = ApplicationUtils.getApplication()
    private lateinit var handlerThread: HandlerThread
    private lateinit var handler: Handler

    fun onCreate() {
        LogUtil.d(TAG, "onCreate: ")
        handlerThread = HandlerThread(TAG)
        handlerThread.start()
        handler = Handler(handlerThread.looper)

        handler.post {
            startWorks()
        }
    }

    fun onDestroy() {
        LogUtil.d(TAG, "onDestroy: ")
        handler.post {
            stopWorks()
            handlerThread.quitSafely()
        }
    }

    private fun startWorks() {
        LocationManager.getInstance().startLocation()
        LocationManager.getInstance().addCallback(locationCallback)
    }

    private fun stopWorks() {
        LocationManager.getInstance().removeCallback(locationCallback)
    }

    private val locationCallback = object : ILocationManager.LocationCallback {
        override fun onLocationFail(errorCode: Int) {
        }

        override fun onLocationSuccess(locationInfo: LocationInfo) {
        }
    }
}