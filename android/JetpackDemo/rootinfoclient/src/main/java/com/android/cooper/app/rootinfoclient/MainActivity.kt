package com.android.cooper.app.rootinfoclient

import android.os.Bundle
import android.os.Looper
import android.os.Message
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.app.common.utils.LogUtil
import com.android.app.common.utils.WeakHandler
import com.android.app.common.utils.execute
import com.android.app.common.utils.serialExecute
import com.rafakob.nsdhelper.NsdHelper
import com.rafakob.nsdhelper.NsdListener
import com.rafakob.nsdhelper.NsdService
import com.rafakob.nsdhelper.NsdType
import com.tamsiree.rxkit.RxNetTool
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), NsdListener {
    companion object {
        private const val TAG = "MainActivity"
    }

    private val uiHandler by lazy {
        UiHandler(this, Looper.getMainLooper())
    }

    private val nsdHelper by lazy {
        NsdHelper(this, this).apply {
            isAutoResolveEnabled = true
            setDiscoveryTimeout(60)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btScan.setOnClickListener {
            if (RxNetTool.isWifiConnected(it.context)) {
                nsdHelper.startDiscovery(NsdType.HTTP)
            }
        }
        btStop.setOnClickListener {
            if (RxNetTool.isWifiConnected(it.context)) {
                nsdHelper.stopDiscovery()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        nsdHelper.stopDiscovery()
    }

    private class UiHandler(
        owner: MainActivity,
        looper: Looper
    ) : WeakHandler<MainActivity>(owner, looper) {
        companion object {
            const val MSG_FIND_DEVICE = 0x01;
        }

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val owner = owner ?: return
            when (msg.what) {
                MSG_FIND_DEVICE -> {
                    owner.tvInfo.append("${msg.obj}\n\n")
                }
            }
        }
    }

    override fun onNsdRegistered(service: NsdService?) {
    }

    override fun onNsdServiceResolved(service: NsdService?) {
        serialExecute {
            service?.run {
                val info = "$name\n${host}\n${hostName}\n${hostIp}\n${port}\n${type}"
                Log.d(TAG, "onNsdServiceResolved: $info")
                name?.run {
                    if (name.contains("FTP")) {
                        val msg = uiHandler.obtainMessage()
                        msg.what = UiHandler.MSG_FIND_DEVICE
                        msg.obj = info
                        uiHandler.sendMessage(msg)
                    }
                }
            }
        }
    }

    override fun onNsdServiceLost(service: NsdService?) {
    }

    override fun onNsdError(p0: String?, p1: Int, p2: String?) {
    }

    override fun onNsdDiscoveryFinished() {
    }

    override fun onNsdServiceFound(service: NsdService?) {
    }
}
