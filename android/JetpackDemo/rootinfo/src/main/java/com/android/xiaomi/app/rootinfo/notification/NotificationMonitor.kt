package com.android.xiaomi.app.rootinfo.notification

import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.os.IBinder
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import androidx.core.app.NotificationCompat
import com.android.app.common.utils.ApplicationUtils
import com.android.app.common.utils.LogUtil

/**
 * Created by cooper
 * 20-6-18.
 * Email: 1239604859@qq.com
 */
class NotificationMonitor : NotificationListenerService() {
    companion object {
        private const val TAG = "NotificationMonitor"
        private const val QQ_PKG = "com.tencent.mobileqq"
        private const val WX_PKG = "com.tencent.mm"
        private val SPECIAL_NOTI = arrayListOf(WX_PKG, QQ_PKG)
        private var isBinded = false

        fun triggerNotificationService() {
            if (isBinded) {
                LogUtil.d(TAG, "already binded: ")
                return
            }
            val context = ApplicationUtils.getApplication().applicationContext
            context.packageManager.setComponentEnabledSetting(
                ComponentName(
                    context,
                    NotificationMonitor::class.java
                ), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP
            )
            context.packageManager.setComponentEnabledSetting(
                ComponentName(
                    context,
                    NotificationMonitor::class.java
                ), PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP
            )
            LogUtil.d(TAG, "triggerNotificationService success: ")
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        LogUtil.d(TAG, "onBind: ")
        isBinded = true
        return super.onBind(intent)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        LogUtil.d(TAG, "onUnbind: ")
        isBinded = false
        return super.onUnbind(intent)
    }

    /*override fun onCreate() {
        super.onCreate()
        LogUtil.d(TAG, "onCreate: ")
    }*/

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)

        uploadNotificationData(sbn)
    }

    /*override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        super.onNotificationRemoved(sbn)
        LogUtil.d(TAG, "onNotificationRemoved: ")
    }

    override fun onListenerConnected() {
        super.onListenerConnected()
        LogUtil.d(TAG, "onListenerConnected: ")
    }

    override fun onListenerDisconnected() {
        super.onListenerDisconnected()
        LogUtil.d(TAG, "onListenerDisconnected: ")
    }*/

    //********************************************************

    private fun uploadNotificationData(sbn: StatusBarNotification?) {
        if (sbn == null) {
            return
        }
        val app = sbn.packageName
        if (!SPECIAL_NOTI.contains(app)) {
            return
        }
        val extras = NotificationCompat.getExtras(sbn.notification)
        val title = extras?.getString(NotificationCompat.EXTRA_TITLE)
        val content = extras?.getString(NotificationCompat.EXTRA_TEXT)
        LogUtil.d(TAG, "app: $app\n title: $title\n content: $content")
    }
}