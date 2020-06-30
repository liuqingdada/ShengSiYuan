package com.android.cooper.app.rootinfoclient

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.android.common.utils.ApplicationUtils
import com.tamsiree.rxkit.RxTool

/**
 * Created by cooper
 * 20-6-19.
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
            RxTool.init(this)
        }
    }
}