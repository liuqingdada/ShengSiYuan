package com.android.logic.common.app

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.android.common.utils.ApplicationUtils
import me.weishu.reflection.Reflection

/**
 * Created by cooper
 * 21-1-12.
 * Email: 1239604859@qq.com
 */
class App : Application() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
        Reflection.unseal(this)
        ApplicationUtils.init(this)
        AppConfig.main()
    }
}