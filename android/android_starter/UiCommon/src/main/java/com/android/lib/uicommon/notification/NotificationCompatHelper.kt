package com.android.lib.uicommon.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationChannelGroup
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.android.common.utils.ApplicationUtils
import com.android.lib.uicommon.R

/**
 * Created by cooper
 * 21-3-25.
 * Email: 1239604859@qq.com
 */
object NotificationCompatHelper {

    class Builder(
        ctx: Context,
        @ChannelIds private val channelId: String,
        private val importance: Int = NotificationManagerCompat.IMPORTANCE_UNSPECIFIED,
    ) : NotificationCompat.Builder(ctx, channelId) {
        override fun build(): Notification {
            ensureChannelInit(channelId, importance)
            return super.build()
        }
    }

    private fun ensureChannelInit(@ChannelIds id: String, importance: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val groupId = getChannelGroup(id)
            val m = context().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            createNotificationChannelGroup(groupId, m)
            createNotificationChannel(id, groupId, m, importance)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannelGroup(
        @ChannelGoup id: String,
        manager: NotificationManager
    ) {
        val group = NotificationChannelGroup(id, getChannelGroupName(id))
        manager.createNotificationChannelGroup(group)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(
        @ChannelIds id: String,
        @ChannelGoup groupId: String,
        manager: NotificationManager,
        importance: Int,
    ) {
        val channel = NotificationChannel(
            id,
            getChannelName(id),
            NotificationManager.IMPORTANCE_DEFAULT
        )
        channel.description = getChannelDesc(id)
        channel.importance = importance
        channel.group = groupId
        manager.createNotificationChannel(channel)
    }

    private fun getChannelGroup(@ChannelIds id: String) = when (id) {
        ChannelIds.ACCOUNT,
        ChannelIds.MEDIA,
        ChannelIds.PUSH,
        ChannelIds.LOCATION,
        ChannelIds.AGENDA -> ChannelGoup.GENERAL

        ChannelIds.SMS,
        ChannelIds.CALL,
        ChannelIds.QQ,
        ChannelIds.WECHAT,
        ChannelIds.FORUM -> ChannelGoup.MESSAGE

        ChannelIds.SYNC,
        ChannelIds.BLUETOOTH -> ChannelGoup.DEVICE

        else -> ChannelGoup.OTHER
    }

    private fun getChannelGroupName(@ChannelGoup groupId: String) = when (groupId) {
        ChannelGoup.GENERAL -> context().getString(R.string.notification_channel_group_general)
        ChannelGoup.MESSAGE -> context().getString(R.string.notification_channel_group_message)
        ChannelGoup.DEVICE -> context().getString(R.string.notification_channel_group_device)
        else -> context().getString(R.string.notification_channel_group_other)
    }

    private fun getChannelName(@ChannelIds id: String) = when (id) {
        ChannelIds.ACCOUNT -> context().getString(R.string.notification_channel_account)
        ChannelIds.MEDIA -> context().getString(R.string.notification_channel_media)
        ChannelIds.PUSH -> context().getString(R.string.notification_channel_push)
        ChannelIds.LOCATION -> context().getString(R.string.notification_channel_location)
        ChannelIds.AGENDA -> context().getString(R.string.notification_channel_agenda)
        ChannelIds.SMS -> context().getString(R.string.notification_channel_sms)
        ChannelIds.CALL -> context().getString(R.string.notification_channel_call)
        ChannelIds.QQ -> context().getString(R.string.notification_channel_qq)
        ChannelIds.WECHAT -> context().getString(R.string.notification_channel_wechat)
        ChannelIds.FORUM -> context().getString(R.string.notification_channel_forum)
        ChannelIds.SYNC -> context().getString(R.string.notification_channel_sync)
        ChannelIds.BLUETOOTH -> context().getString(R.string.notification_channel_bluetooth)
        else -> context().getString(R.string.notification_channel_other)
    }

    private fun getChannelDesc(@ChannelIds id: String) = when (id) {
        ChannelIds.LOCATION -> context().getString(R.string.notification_channel_location_desc)
        ChannelIds.PUSH -> context().getString(R.string.notification_channel_push_desc)
        ChannelIds.AGENDA -> context().getString(R.string.notification_channel_agenda_desc)
        ChannelIds.FORUM -> context().getString(R.string.notification_channel_forum_desc)
        else -> null
    }

    private fun context() = ApplicationUtils.getApplication()
}