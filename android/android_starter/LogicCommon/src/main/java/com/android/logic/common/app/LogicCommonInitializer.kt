package com.android.logic.common.app

import android.content.Context
import androidx.startup.Initializer
import com.airbnb.mvrx.Mavericks
import com.alibaba.android.arouter.launcher.ARouter
import com.android.common.utils.ApplicationUtils
import com.android.lib.datastore.DsManager
import com.android.lib.netcommon.http.HttpService

/**
 * Created by cooper
 * 21-4-14.
 * Email: 1239604859@qq.com
 */
class LogicCommonInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        DsManager.init()
        Mavericks.initialize(context)
        HttpService.init()

        if (AppConfig.isDebug()) {
            ARouter.openLog()  // 打印日志
            ARouter.openDebug()// 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(ApplicationUtils.getApplication())
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return arrayListOf()
    }
}