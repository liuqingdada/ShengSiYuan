package com.android.lib.uicommon.notification;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@StringDef({
        ChannelGoup.GENERAL,
        ChannelGoup.MESSAGE,
        ChannelGoup.DEVICE,
        ChannelGoup.OTHER
})
public @interface ChannelGoup {
    String GENERAL = "general";
    String MESSAGE = "message";
    String DEVICE = "device";
    String OTHER = "other";
}