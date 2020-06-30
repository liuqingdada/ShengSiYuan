package com.android.cooper.app.workmanager

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.android.common.utils.ApplicationUtils
import com.android.cooper.app.workmanager.log.LogUtilTree
import com.android.cooper.app.workmanager.matrix.MatrixHelper

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

        LogUtilTree.main()

        MatrixHelper.main()
    }
}