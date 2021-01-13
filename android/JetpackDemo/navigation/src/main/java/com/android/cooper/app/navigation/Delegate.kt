package com.android.cooper.app.navigation

import android.app.Activity
import android.content.Intent

/**
 * Created by cooper
 * 21-1-12.
 * Email: 1239604859@qq.com
 */
inline fun <reified T : Activity> Activity.startActivity() {
    startActivity(Intent(this, T::class.java).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    })
}
