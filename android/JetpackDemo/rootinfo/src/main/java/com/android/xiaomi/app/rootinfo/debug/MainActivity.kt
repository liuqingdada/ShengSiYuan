package com.android.xiaomi.app.rootinfo.debug

import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.common.utils.ApplicationUtils
import com.tamsiree.rxkit.RxActivityTool
import com.tamsiree.rxkit.RxAppTool
import com.tamsiree.rxkit.RxFileTool
import com.tamsiree.rxkit.RxImageTool
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(0, 0)
        launchXiaomiAccount()
        ApplicationUtils.getHandler().postDelayed({
            finish()
        }, 2000)
    }

    private fun launchXiaomiAccount() {
        try {
            val app = "com.xiaomi.account"
            val activity = ".ui.AccountSettingsActivity"
            RxActivityTool.launchActivity(this, app, "$app$activity")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun saveAppIcons() {
        try {
            val app = "com.miui.cloudservice"
            val iconDrawable = RxAppTool.getAppIcon(this, app)
            val data = RxImageTool.drawable2Bytes(iconDrawable!!, Bitmap.CompressFormat.PNG)
            val file = File(getExternalFilesDir(null), "ic_launcher.png")
            val outputStream = BufferedOutputStream(FileOutputStream(file))
            outputStream.write(data)
            outputStream.flush()
            outputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
