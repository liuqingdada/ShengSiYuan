package com.android.common.utils;

import android.Manifest;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

import androidx.annotation.IntDef;
import androidx.annotation.RequiresPermission;
import androidx.annotation.VisibleForTesting;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class NetworkUtils {
    private static final String TAG = "NetworkUtils";

    public static final int NETWORK_TYPE_NONE = -1;
    public static final int NETWORK_TYPE_WIFI = 1;
    public static final int NETWORK_TYPE_MOBILE = 2;
    public static final int NETWORK_TYPE_BLUETOOTH = 3;
    public static final int NETWORK_TYPE_BLUETOOTH_MOBILE = 4;
    public static final int NETWORK_TYPE_BLUETOOTH_WIFI = 5;
    public static final int NETWORK_TYPE_2G = 10;
    public static final int NETWORK_TYPE_3G = 11;
    public static final int NETWORK_TYPE_4G = 12;

    public static final int TYPE_WEAR_OS_COMPANION_PROXY = 16;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            NETWORK_TYPE_NONE, NETWORK_TYPE_WIFI, NETWORK_TYPE_MOBILE,
            NETWORK_TYPE_BLUETOOTH, NETWORK_TYPE_BLUETOOTH_MOBILE, NETWORK_TYPE_BLUETOOTH_WIFI,
            NETWORK_TYPE_2G, NETWORK_TYPE_3G, NETWORK_TYPE_4G
    })
    public @interface NetworkType {}

    public static class NetworkDetailedInfo {
        public boolean connected;
        public int type;
        public int subType;
        public @NetworkType int mixedType;

        public NetworkDetailedInfo(boolean connected, int type, int subType, int mixedType) {
            this.connected = connected;
            this.type = type;
            this.subType = subType;
            this.mixedType = mixedType;
        }
    }

    private NetworkUtils() {
        // disable the default constructor
    }

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static NetworkInfo getActiveNetworkInfo(Context cxt) {
        ConnectivityManager cm = (ConnectivityManager) cxt.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
            LogUtil.w(TAG, "failed to get connectivity service");
            return null;
        }

        NetworkInfo netInfo = null;
        try {
            netInfo = cm.getActiveNetworkInfo();
        } catch (Exception e) {
            LogUtil.w(TAG, "failed to get active network info", e);
        }
        return netInfo;
    }

    /**
     * @return One of the values {@link #NETWORK_TYPE_NONE},
     *         {@link #NETWORK_TYPE_WIFI}, {@link #NETWORK_TYPE_MOBILE},
     *         {@link #NETWORK_TYPE_BLUETOOTH}, {@link #NETWORK_TYPE_BLUETOOTH_MOBILE}
     *         or {@link #NETWORK_TYPE_BLUETOOTH_WIFI}
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    @NetworkType
    public static int getSimpleNetworkType(Context cxt) {
        NetworkInfo netInfo = getActiveNetworkInfo(cxt);
        if (netInfo == null || !netInfo.isConnected()) {
            return NETWORK_TYPE_NONE;
        }

        return getNetworkType(netInfo.getType(), netInfo.getSubtype());
    }

    /**
     * @param type A value from {@link android.net.NetworkInfo#getType()}
     * @return One of the values {@link #NETWORK_TYPE_NONE},
     *         {@link #NETWORK_TYPE_WIFI}, {@link #NETWORK_TYPE_MOBILE}
     *         {@link #NETWORK_TYPE_BLUETOOTH}, {@link #NETWORK_TYPE_BLUETOOTH_MOBILE}
     *         or {@link #NETWORK_TYPE_BLUETOOTH_WIFI}
     */
    @NetworkType
    @VisibleForTesting
    static int getNetworkType(int type, int subType) {
        if (type == ConnectivityManager.TYPE_WIFI
                || type == ConnectivityManager.TYPE_WIMAX
                || type == ConnectivityManager.TYPE_ETHERNET) {
            return NETWORK_TYPE_WIFI;
        } else if (type == ConnectivityManager.TYPE_MOBILE
                || type == ConnectivityManager.TYPE_MOBILE_MMS) {
            return NETWORK_TYPE_MOBILE;
        } else if (type == ConnectivityManager.TYPE_BLUETOOTH) {
            // Ticwear
            if (subType == ConnectivityManager.TYPE_MOBILE) {
                return NETWORK_TYPE_BLUETOOTH_MOBILE;
            } else if(subType == ConnectivityManager.TYPE_WIFI) {
                return NETWORK_TYPE_BLUETOOTH_WIFI;
            } else {
                return NETWORK_TYPE_BLUETOOTH;
            }
        } else if (type == TYPE_WEAR_OS_COMPANION_PROXY) {
            // Wear OS
            return NETWORK_TYPE_BLUETOOTH;
        }
        return NETWORK_TYPE_NONE; // Take unknown networks as none
    }

    private static int getTelephonyNetworkType(Context cxt) {
        TelephonyManager tm = (TelephonyManager) cxt.getSystemService(Context.TELEPHONY_SERVICE);
        if (tm == null) {
            LogUtil.w(TAG, "failed to get telephony service");
            return TelephonyManager.NETWORK_TYPE_UNKNOWN;
        }

        try {
            return tm.getNetworkType();
        } catch (Exception e) {
            LogUtil.w(TAG, "failed to get telephony network type", e);
        }
        return TelephonyManager.NETWORK_TYPE_UNKNOWN;
    }

    /**
     * @return One of values {@link #NETWORK_TYPE_2G}, {@link #NETWORK_TYPE_3G},
     *                       {@link #NETWORK_TYPE_4G} or {@link #NETWORK_TYPE_NONE}
     */
    @NetworkType
    private static int getMobileNetworkType(Context cxt) {
        int tmType = getTelephonyNetworkType(cxt);
        return getMobileNetworkType(tmType);
    }

    /**
     * @param tmType A value from {@link TelephonyManager#getNetworkType()}
     * @return One of values {@link #NETWORK_TYPE_2G}, {@link #NETWORK_TYPE_3G},
     *                       {@link #NETWORK_TYPE_4G} or {@link #NETWORK_TYPE_NONE}
     */
    @NetworkType
    private static int getMobileNetworkType(int tmType) {
        // Code from android-5.1.1_r4:
        // frameworks/base/packages/SystemUI/src/com/android/systemui/statusbar/policy/NetworkControllerImpl.java
        // in NetworkControllerImpl#mapIconSets()
        switch (tmType) {
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                return NETWORK_TYPE_NONE;

            case TelephonyManager.NETWORK_TYPE_LTE:
                return NETWORK_TYPE_4G;

            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_UMTS:
//            case TelephonyManager.NETWORK_TYPE_TD_SCDMA:
                return NETWORK_TYPE_3G;

            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return NETWORK_TYPE_3G; // H

            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
//            case TelephonyManager.NETWORK_TYPE_GSM:
                return NETWORK_TYPE_2G;
        }
        return NETWORK_TYPE_2G;
    }

    /**
     * @return One of values {@link #NETWORK_TYPE_NONE},
     *         {@link #NETWORK_TYPE_WIFI}, {@link #NETWORK_TYPE_2G},
     *         {@link #NETWORK_TYPE_3G}, {@link #NETWORK_TYPE_4G},
     *         {@link #NETWORK_TYPE_BLUETOOTH}, {@link #NETWORK_TYPE_BLUETOOTH_MOBILE}
     *         or {@link #NETWORK_TYPE_BLUETOOTH_WIFI}
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    @NetworkType
    public static int getMixedNetworkType(Context cxt) {
        int type = getSimpleNetworkType(cxt);
        if (type == NETWORK_TYPE_MOBILE) {
            type = getMobileNetworkType(cxt);
        }
        return type;
    }

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static NetworkDetailedInfo getNetworkDetailedInfo(Context cxt) {
        NetworkInfo networkInfo = NetworkUtils.getActiveNetworkInfo(cxt);
        if (networkInfo != null && networkInfo.isConnected()) {
            int type = networkInfo.getType();
            int subType = networkInfo.getSubtype();
            int mixedType = getNetworkType(type, subType);
            if (mixedType == NETWORK_TYPE_MOBILE) {
                subType = getTelephonyNetworkType(cxt);
                mixedType = getMobileNetworkType(subType);
            }
            return new NetworkDetailedInfo(networkInfo.isConnected(), type, subType, mixedType);
        }
        return new NetworkDetailedInfo(false, -1, -1, NETWORK_TYPE_NONE);
    }

    /**
     * Check if there is an active network connection
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static boolean isNetworkAvailable(Context cxt) {
        NetworkInfo network = getActiveNetworkInfo(cxt);
        return network != null && network.isConnected();
    }

    /**
     * Check if wifi is available(The watch itself or the connected phone).
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static boolean isWifiAvailable(Context cxt) {
        @NetworkType int netType = getSimpleNetworkType(cxt);
        return (NETWORK_TYPE_WIFI == netType || NETWORK_TYPE_BLUETOOTH_WIFI == netType);
    }

    @RequiresPermission(Manifest.permission.ACCESS_WIFI_STATE)
    public static boolean isWifiConnected(Context cxt) {
        WifiManager
                wifiManager = (WifiManager) cxt.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (wifiManager.isWifiEnabled()) {
            WifiInfo info = wifiManager.getConnectionInfo();
            return info != null && info.getNetworkId() != -1;
        }
        return false;
    }

    /**
     * Check whether network is blocked when network is not connected
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static boolean isNetworkBlocked(Context cxt) {
        NetworkInfo network = getActiveNetworkInfo(cxt);
        return network != null && !network.isConnected()
                && network.getDetailedState() == NetworkInfo.DetailedState.BLOCKED;
    }

    /**
     * Network is connected with proxy (BT).
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static boolean isProxyNetwork(Context cxt) {
        NetworkInfo network = getActiveNetworkInfo(cxt);
        return network != null &&
                (network.getType() == TYPE_WEAR_OS_COMPANION_PROXY ||
                        network.getType() == ConnectivityManager.TYPE_BLUETOOTH);
    }
}
