package com.android.common.utils

/**
 * Created by cooper
 * 21-4-28.
 * Email: 1239604859@qq.com
 */
fun ByteArray.toHexString() = joinToString("") { "%02x".format(it) }
