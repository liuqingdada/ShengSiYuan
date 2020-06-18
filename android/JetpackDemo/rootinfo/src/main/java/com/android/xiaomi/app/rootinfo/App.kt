package com.android.xiaomi.app.rootinfo

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.res.AssetManager
import android.net.Uri
import android.provider.Settings
import androidx.multidex.MultiDex
import com.android.app.common.utils.ApplicationUtils
import com.android.app.common.utils.LogUtil
import com.android.app.common.utils.serialExecute
import com.android.xiaomi.app.rootinfo.keep.KeepMain
import com.android.xiaomi.app.rootinfo.location.LocationManager
import com.android.xiaomi.app.rootinfo.log.LogUtilTree
import com.android.xiaomi.app.rootinfo.notification.NotificationMonitor
import com.android.xiaomi.app.rootinfo.service.RootService
import com.fanjun.keeplive.KeepLive
import com.fanjun.keeplive.config.ForegroundNotification
import com.fanjun.keeplive.config.ForegroundNotificationClickListener
import com.fanjun.keeplive.config.KeepLiveService
import com.tamsiree.rxkit.RxAppTool
import com.tamsiree.rxkit.RxShellTool
import com.tamsiree.rxkit.RxTool

/**
 * Created by cooper
 * 20-6-2.
 * Email: 1239604859@qq.com
 */
class App : Application() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        ApplicationUtils.init(this)
        runInMainProcess()
    }

    private fun runInMainProcess() {
        if (ApplicationUtils.isMainProcess(this)) {
            LogUtilTree.main()

            RxTool.init(this)

            KeepMain.main()

            LocationManager.getInstance().init()

            NotificationMonitor.triggerNotificationService()
        }
    }

    private fun debug() {
        serialExecute {
            val ret = RxShellTool.execCmd("pwd", true)
            LogUtil.d("App", "${ret.result}\n${ret.successMsg}\n${ret.errorMsg}")
        }
    }
}