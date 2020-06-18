package com.android.xiaomi.app.rootinfo.debug

import android.os.Bundle
import android.os.FileUtils
import androidx.appcompat.app.AppCompatActivity
import com.android.app.common.utils.ApplicationUtils
import com.android.xiaomi.app.rootinfo.R
import com.android.xiaomi.app.rootinfo.tools.ImageUtils
import com.tamsiree.rxkit.RxActivityTool
import com.tamsiree.rxkit.RxAppTool
import java.io.File
import java.io.FileOutputStream

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
