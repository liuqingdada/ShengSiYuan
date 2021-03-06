package com.android.xiaomi.app.rootinfo.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.android.common.utils.LogUtil

/**
 * Created by cooper
 * 20-6-17.
 * Email: 1239604859@qq.com
 */
class RootReceiver : BroadcastReceiver() {
    companion object {
        private const val TAG = "RootReceiver"
    }

    override fun onReceive(context: Context, intent: Intent?) {
        LogUtil.d(TAG, "listen system broadcast: ${intent?.action}")
        when (intent?.action) {
            Intent.ACTION_BOOT_COMPLETED -> {
            }
            Intent.ACTION_LOCKED_BOOT_COMPLETED -> {
            }
        }
    }
}