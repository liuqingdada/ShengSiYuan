package com.android.lib.uicommon.theme

import android.content.Context
import android.content.res.Configuration
import android.util.TypedValue
import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatDelegate

/**
 * Created by cooper
 * 2021/5/27.
 * Email: 1239604859@qq.com
 *
 * 在绝大多数情况下，让应用程序跟随系统的设置来决定使用浅色主题还是深色主题是最合适的一种做法
 * 然而如果你一定想要脱离系统设置，让自己的应用程序独立控制使用浅色主题还是深色主题，
 * Android对此也是支持的，只要使用 [AppCompatDelegate] setDefaultNightMode 方法即可
 *
 * 禁止切换主题activity重新创建：
 * <activity
 * android:name=".MainActivity"
 * android:configChanges="uiMode" />
 *
 * override fun onConfigurationChanged(newConfig: Configuration) {
 * val currentNightMode = newConfig.uiMode and Configuration.UI_MODE_NIGHT_MASK
 *   when (currentNightMode) {
 *     Configuration.UI_MODE_NIGHT_NO -> {} // 夜间模式未启用，使用浅色主题
 *     Configuration.UI_MODE_NIGHT_YES -> {} // 夜间模式启用，使用深色主题
 *   }
 * }
 */

fun Context.isDarkTheme(): Boolean {
    val flag = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
    return flag == Configuration.UI_MODE_NIGHT_YES
}

@ColorInt
fun View.getThemeAttrColor(@AttrRes resid: Int): Int {
    val typedValue = TypedValue()
    context.theme.resolveAttribute(
        resid,
        typedValue,
        true
    )
    return typedValue.data
}
