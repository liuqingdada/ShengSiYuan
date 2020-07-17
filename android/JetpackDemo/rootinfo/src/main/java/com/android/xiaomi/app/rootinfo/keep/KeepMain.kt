package com.android.xiaomi.app.rootinfo.keep

import com.android.common.utils.ApplicationUtils
import com.android.xiaomi.app.rootinfo.R
import com.android.xiaomi.app.rootinfo.service.RootService
import com.fanjun.keeplive.KeepLive
import com.fanjun.keeplive.config.ForegroundNotification
import com.fanjun.keeplive.config.ForegroundNotificationClickListener
import com.fanjun.keeplive.config.KeepLiveService

/**
 * Created by cooper
 * 20-6-18.
 * Email: 1239604859@qq.com
 */
object KeepMain {
    private val context = ApplicationUtils.getApplication()
    private val rootService = RootService()

    fun main() {
        val notification = ForegroundNotification(
            context.getString(R.string.app_name),
            "",
            R.mipmap.ic_launcher,
            ForegroundNotificationClickListener { _, _ ->
            })
        KeepLive.startWork(
            context,
            KeepLive.RunMode.ROGUE,
            notification,
            object : KeepLiveService {
                override fun onWorking() {
                    rootService.onCreate()
                }

                override fun onStop() {
                    rootService.onDestroy()
                }
            })
    }
}