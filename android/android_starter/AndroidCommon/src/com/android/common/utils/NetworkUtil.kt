package com.android.common.utils

import com.blankj.utilcode.util.NetworkUtils

/**
 * Created by cooper
 * 2021/5/8.
 * Email: 1239604859@qq.com
 */
object NetworkUtil {
    @NetworkType
    fun type2Int(type: NetworkUtils.NetworkType?): Int = when (type) {
        NetworkUtils.NetworkType.NETWORK_2G -> NetworkType.NETWORK_2G
        NetworkUtils.NetworkType.NETWORK_3G -> NetworkType.NETWORK_3G
        NetworkUtils.NetworkType.NETWORK_4G -> NetworkType.NETWORK_4G
        NetworkUtils.NetworkType.NETWORK_5G -> NetworkType.NETWORK_5G
        NetworkUtils.NetworkType.NETWORK_WIFI -> NetworkType.NETWORK_WIFI
        NetworkUtils.NetworkType.NETWORK_ETHERNET -> NetworkType.NETWORK_ETHERNET
        NetworkUtils.NetworkType.NETWORK_UNKNOWN -> NetworkType.NETWORK_UNKNOWN
        NetworkUtils.NetworkType.NETWORK_NO -> NetworkType.NETWORK_NO
        else -> NetworkType.NETWORK_NULL
    }

    fun init(listener: NetworkUtils.OnNetworkStatusChangedListener) {
        if (!NetworkUtils.isRegisteredNetworkStatusChangedListener(listener)) {
            NetworkUtils.registerNetworkStatusChangedListener(listener)
        }
    }

    @NetworkType
    fun getNetworkType() = type2Int(NetworkUtils.getNetworkType())

    fun getIPAddress(useIPv4: Boolean): String = NetworkUtils.getIPAddress(useIPv4)

    fun isNetworkAvailable() = NetworkUtils.isAvailable()
}