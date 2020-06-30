package com.android.xiaomi.app.rootinfo.debug

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.common.utils.ApplicationUtils
import com.tamsiree.rxkit.RxActivityTool

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(0, 0)
        try {
            val app = "com.miui.securitycenter"
            val activity = RxActivityTool.getLauncherActivity(this, app)
            RxActivityTool.launchActivity(this, app, activity)
        } catch (e: Exception) {
        }
        ApplicationUtils.getHandler().postDelayed({
            finish()
        }, 2000)
    }
}
