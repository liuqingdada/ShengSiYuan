package com.android.cooper.app.paging

import android.app.Application
import com.android.common.utils.ApplicationUtils

/**
 * Created by cooper
 * 20-12-16.
 * Email: 1239604859@qq.com
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        ApplicationUtils.init(this)
    }
}