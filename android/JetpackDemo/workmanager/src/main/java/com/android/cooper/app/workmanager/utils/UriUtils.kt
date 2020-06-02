package com.android.cooper.app.workmanager.utils

import android.content.Context
import android.net.Uri
import android.os.Build
import androidx.core.content.FileProvider
import java.io.File

/**
 * Created by cooper
 * 20-6-2.
 * Email: 1239604859@qq.com
 */
object UriUtils {
    fun getFileUri(context: Context, file: File): Uri =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            FileProvider.getUriForFile(
                context,
                context.packageName + ".file.path.share",
                file
            )
        } else Uri.fromFile(file)
}