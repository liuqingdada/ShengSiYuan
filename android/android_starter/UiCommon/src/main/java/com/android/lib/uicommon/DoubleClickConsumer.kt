package com.android.lib.uicommon

/**
 * Created by cooper
 * 21-4-1.
 * Email: 1239604859@qq.com
 */
object DoubleClickConsumer {
    var lastTime: Long = 0

    /**
     * 处理快速点击的类
     */
    fun doubleClick(): Boolean {
        if (System.currentTimeMillis() - lastTime < 500) {
            return true
        }
        lastTime = System.currentTimeMillis()
        return false
    }
}