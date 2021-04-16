package com.android.lib.uicommon.notification;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@StringDef({
        ChannelIds.ACCOUNT,
        ChannelIds.MEDIA,
        ChannelIds.PUSH,
        ChannelIds.AGENDA,
        ChannelIds.LOCATION,

        ChannelIds.SMS,
        ChannelIds.CALL,
        ChannelIds.QQ,
        ChannelIds.WECHAT,
        ChannelIds.FORUM,

        ChannelIds.SYNC,
        ChannelIds.BLUETOOTH,

        ChannelIds.OTHER
})
public @interface ChannelIds {
    String ACCOUNT = "account";
    String MEDIA = "media";
    String PUSH = "push";
    String AGENDA = "agenda";
    String LOCATION = "location";

    String SMS = "SMS";
    String CALL = "call";
    String QQ = "qq";
    String WECHAT = "wechat";
    String FORUM = "forum";

    String SYNC = "sync";
    String BLUETOOTH = "bluetooth";

    String OTHER = "other";
}