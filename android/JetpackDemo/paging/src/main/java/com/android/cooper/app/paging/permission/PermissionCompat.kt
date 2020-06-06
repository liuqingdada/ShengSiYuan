package com.android.cooper.app.paging.permission

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.annotation.IntRange
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

/**
 * Created by liuqing.yang
 * 2020/6/6.
 * Email: 1239604859@qq.com
 */
object PermissionCompat {
    /**
     * @param permission String [Manifest.permission] [Manifest.permission_group]
     */
    fun checkPermission(context: Context, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun requestPermission(activity: Activity, permission: String, @IntRange(from = 0) requestCode: Int) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
            // 给用户解释, 用户跳到设置界面
        } else {
            ActivityCompat.requestPermissions(activity, arrayOf(permission), requestCode)
        }
    }
}