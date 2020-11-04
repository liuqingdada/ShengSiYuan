package com.android.xiaomi.app.rootinfo.notification

import android.app.NotificationManager
import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.os.IBinder
import android.provider.Settings
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.text.TextUtils
import androidx.core.app.NotificationCompat
import com.android.common.utils.ApplicationUtils
import com.android.common.utils.LogUtil

/**
 * Created by cooper
 * 20-6-18.
 * Email: 1239604859@qq.com
 */
class NotificationMonitor : NotificationListenerService() {
    companion object {
        private const val TAG = "NotificationMonitor"

        private const val ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners"
        private const val ENABLED_NOTIFICATION_LISTENERS_VALIDATOR = ":"

        private const val QQ_PKG = "com.tencent.mobileqq"
        private const val WX_PKG = "com.tencent.mm"
        private val SPECIAL_NOTI = arrayListOf(WX_PKG, QQ_PKG)

        private val context = ApplicationUtils.getApplication().applicationContext
        private var isBinded = false

        fun triggerNotificationService() {
            if (!isNotificationListenerAccessGranted()) {
                openNotificationListener()
            }
            bindService()
        }

        private fun isNotificationListenerAccessGranted(): Boolean {
            val nm = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            val isNotificationListenerAccessGranted = nm.isNotificationListenerAccessGranted(
                ComponentName(
                    context,
                    NotificationMonitor::class.java
                )
            )
            LogUtil.d(
                TAG,
                "isNotificationListenerAccessGranted: $isNotificationListenerAccessGranted"
            )
            return isNotificationListenerAccessGranted
        }

        private fun isEnable(): Boolean {
            val packageName = context.packageName
            val flat = Settings.Secure.getString(
                context.contentResolver, ENABLED_NOTIFICATION_LISTENERS
            )
            LogUtil.d(TAG, "isEnable: $flat")
            if (!flat.isNullOrBlank()) {
                val names = flat.split(ENABLED_NOTIFICATION_LISTENERS_VALIDATOR)
                names.forEach {
                    val component = ComponentName.unflattenFromString(it)
                    if (component != null) {
                        if (packageName == component.packageName) {
                            return true
                        }
                    }
                }
            }
            return false
        }

        private fun openNotificationPolicy() {
            context.startActivity(Intent().apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                action = Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS
            })
        }

        private fun openNotificationListener() {
            context.startActivity(Intent().apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                action = Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS
            })
        }

        private fun bindService() {
            if (isBinded) {
                LogUtil.d(TAG, "already binded: ")
                return
            }
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
            LogUtil.d(TAG, "bindService: ")
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