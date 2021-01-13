package com.android.cooper.app.navigation

import android.app.Application
import com.android.common.utils.ApplicationUtils

/**
 * Created by cooper
 * 21-1-12.
 * Email: 1239604859@qq.com
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        ApplicationUtils.init(this)
    }
}