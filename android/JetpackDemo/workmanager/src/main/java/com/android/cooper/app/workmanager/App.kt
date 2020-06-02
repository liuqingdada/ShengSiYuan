package com.android.cooper.app.workmanager

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

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
}