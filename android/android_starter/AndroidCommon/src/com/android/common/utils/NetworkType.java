package com.android.common.utils;

import androidx.annotation.IntDef;

/**
 * Created by cooper
 * 2021/5/8.
 * Email: 1239604859@qq.com
 */
@IntDef({
        NetworkType.NETWORK_5G,
        NetworkType.NETWORK_4G,
        NetworkType.NETWORK_3G,
        NetworkType.NETWORK_2G,
        NetworkType.NETWORK_WIFI,
        NetworkType.NETWORK_ETHERNET,
        NetworkType.NETWORK_UNKNOWN,
        NetworkType.NETWORK_NO,
        NetworkType.NETWORK_NULL,
})
public @interface NetworkType {
    int NETWORK_5G = 5;
    int NETWORK_4G = 4;
    int NETWORK_3G = 3;
    int NETWORK_2G = 2;
    int NETWORK_WIFI = 1024;
    int NETWORK_ETHERNET = 1023;
    int NETWORK_UNKNOWN = 1;
    int NETWORK_NO = 0;
    int NETWORK_NULL = -1;
}
