package com.android.xiaomi.app.rootinfo

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.android.common.utils.ApplicationUtils
import com.android.common.utils.LogUtil
import com.android.common.utils.ProcessUtil
import com.android.common.utils.serialExecute
import com.android.xiaomi.app.rootinfo.keep.KeepMain
import com.android.xiaomi.app.rootinfo.location.LocationManager
import com.android.xiaomi.app.rootinfo.log.LogUtilTree
import com.android.xiaomi.app.rootinfo.notification.NotificationMonitor
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
        if (ProcessUtil.isMainProcess(this)) {
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