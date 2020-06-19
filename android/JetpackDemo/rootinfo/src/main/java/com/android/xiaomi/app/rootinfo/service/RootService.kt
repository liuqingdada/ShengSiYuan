package com.android.xiaomi.app.rootinfo.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import be.ppareit.swiftp.FsService
import be.ppareit.swiftp.FsSettings
import be.ppareit.swiftp.FtpInitializer
import com.android.app.common.utils.ApplicationUtils
import com.android.app.common.utils.LogUtil
import com.android.xiaomi.app.rootinfo.location.LocationManager
import com.tamsiree.rxkit.RxNetTool

/**
 * Created by cooper
 * 20-6-17.
 * Email: 1239604859@qq.com
 */
class RootService {
    companion object {
        private const val TAG = "RootService"
        private const val PORT: Int = 8899
    }

    private val context = ApplicationUtils.getApplication().applicationContext

    private val connectivityManager by lazy {
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    private lateinit var handlerThread: HandlerThread
    private lateinit var handler: Handler

    fun onCreate() {
        LogUtil.d(TAG, "onCreate: ")
        handlerThread = HandlerThread(TAG)
        handlerThread.start()
        handler = Handler(handlerThread.looper)

        handler.post {
            try {
                startWorks()
            } catch (e: Exception) {
            }
        }
    }

    fun onDestroy() {
        LogUtil.d(TAG, "onDestroy: ")
        handler.post {
            try {
                stopWorks()
            } catch (e: Exception) {
            }
            handlerThread.quitSafely()
        }
    }

    private fun startWorks() {
        LocationManager.getInstance().startLocation()

        val fsIntentFilter = IntentFilter()
        fsIntentFilter.addAction(FsService.ACTION_STARTED)
        fsIntentFilter.addAction(FsService.ACTION_STOPPED)
        context.registerReceiver(fsActionReceiver, fsIntentFilter)
        FtpInitializer.onCreate()

        connectivityManager.registerNetworkCallback(
            NetworkRequest.Builder().build(),
            networkCallback
        )
    }

    private fun stopWorks() {
        context.unregisterReceiver(fsActionReceiver)

        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

    @Suppress("DEPRECATION")
    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onLost(network: Network?) {
            if (!RxNetTool.isWifiConnected(context)) {
                Log.i(TAG, "WIFI 断开 ")
                FsService.stop()
            }
        }

        override fun onAvailable(network: Network?) {
            val networkInfo = connectivityManager.getNetworkInfo(network)
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
            if (networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true) {
                Log.i(TAG, "WIFI 连接到网络 ")
                FsService.start()
            }
        }
    }

    private val fsActionReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.d(TAG, "onReceive: ${intent?.action}")
            when (intent?.action) {
                FsService.ACTION_STARTED -> {
                    val ftpAddress = getFtpAddress()
                    Log.d(TAG, "onReceive: $ftpAddress")
                }
                FsService.ACTION_STOPPED -> {
                }
            }
        }
    }

    private fun getFtpAddress(): String? {
        var ipTxt: String? = null
        val fsRunning = FsService.isRunning()
        if (fsRunning) {
            val address = FsService.getLocalInetAddress() ?: return null
            ipTxt = "ftp://${address.hostAddress}:${FsSettings.getPortNumber()}/"
        }
        return ipTxt
    }
}