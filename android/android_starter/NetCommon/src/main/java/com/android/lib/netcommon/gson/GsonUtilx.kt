package com.android.lib.netcommon.gson

import com.google.gson.reflect.TypeToken

/**
 * Created by cooper
 * 21-4-29.
 * Email: 1239604859@qq.com
 */
object GsonUtilx {
    inline fun <reified T> fromJson(json: String): T {
        check(json.isNotEmpty() || json.isNotBlank())
        return GsonUtils.fromJson(json, object : TypeToken<T>() {}.type)
    }
}
