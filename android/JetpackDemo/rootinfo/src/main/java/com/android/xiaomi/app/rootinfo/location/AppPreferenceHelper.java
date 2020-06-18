package com.android.xiaomi.app.rootinfo.location;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.android.app.common.utils.ApplicationUtils;

/**
 * 存储一些普通信息
 */
public class AppPreferenceHelper {

    private static final String KEY_LOCATION_INFO = "location_info";

    private AppPreferenceHelper() {
    }

    private static SharedPreferences getPrefs() {
        return PreferenceManager.getDefaultSharedPreferences(ApplicationUtils.getApplication());
    }

    public static String getLocationInfo() {
        return getPrefs().getString(KEY_LOCATION_INFO, null);
    }

    public static void setLocationInfo(String locationInfo) {
        getPrefs().edit().putString(KEY_LOCATION_INFO, locationInfo).apply();
    }
}