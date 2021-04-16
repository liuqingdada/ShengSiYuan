package com.android.lib.uicommon.view

import android.content.Context
import android.graphics.Point
import android.hardware.display.DisplayManager
import android.view.Display
import android.view.View

/**
 * Created by cooper
 * 21-4-8.
 * Email: 1239604859@qq.com
 */

fun View.dp2px(dipValue: Float): Float {
    val scale = context.resources.displayMetrics.density
    return dipValue * scale + 0.5f
}

fun View.px2dp(pxValue: Float): Float {
    val scale = context.resources.displayMetrics.density
    return pxValue / scale + 0.5f
}

fun View.px2sp(pxValue: Float): Float {
    val fontScale = context.resources.displayMetrics.scaledDensity
    return pxValue / fontScale + 0.5f
}

fun View.sp2px(spValue: Float): Float {
    val fontScale = context.resources.displayMetrics.scaledDensity
    return spValue * fontScale + 0.5f
}

fun View.getScreenRealHeight(): Int {
    val size = Point()
    val dm = context.getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
    dm.getDisplay(Display.DEFAULT_DISPLAY).getRealSize(size)
    return size.y
}

fun View.getScreenRealWidth(): Int {
    val size = Point()
    val dm = context.getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
    dm.getDisplay(Display.DEFAULT_DISPLAY).getRealSize(size)
    return size.x
}