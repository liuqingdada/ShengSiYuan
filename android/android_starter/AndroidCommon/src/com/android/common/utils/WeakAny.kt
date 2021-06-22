package com.android.common.utils

import java.lang.ref.WeakReference

/**
 * @author: cooper
 * Date: 20-7-20.
 * Email: yangliuqing@xiaomi.com
 */
abstract class WeakAny<out T>(owner: T) {
    private val weakOwner = WeakReference<T>(owner)

    fun getOwner(): T? = weakOwner.get()
}
