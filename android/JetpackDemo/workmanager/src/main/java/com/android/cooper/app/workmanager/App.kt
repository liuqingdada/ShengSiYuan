package com.android.cooper.app.workmanager

import android.app.Application
import android.content.Context
import android.text.TextUtils
import androidx.multidex.MultiDex
import com.android.app.common.log.LogUtilTree
import com.android.app.common.utils.ApplicationUtils
import com.android.app.common.utils.CrashHandler

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

        val processNameSuffix = getCurrentProcessNameSuffix()
        val isMainpProcess = TextUtils.isEmpty(processNameSuffix)
        if (isMainpProcess) {
            LogUtilTree(BuildConfig.DEBUG, processNameSuffix).init()
        }
        CrashHandler.getInstance().init(this)
    }

    private fun getCurrentProcessNameSuffix(): String {
        val processName = ApplicationUtils.getCurrentProcessName(this)
        return ApplicationUtils.getCurrentProcessNameSuffix(processName)
    }
}