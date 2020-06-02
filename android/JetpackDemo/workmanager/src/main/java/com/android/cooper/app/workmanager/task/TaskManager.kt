package com.android.cooper.app.workmanager.task

import android.content.Context
import android.os.Build
import androidx.lifecycle.Observer
import androidx.work.*
import com.android.cooper.app.workmanager.utils.UriUtils
import java.util.concurrent.TimeUnit

/**
 * Created by cooper
 * 20-6-2.
 * Email: 1239604859@qq.com
 */
object TaskManager {
    fun submitUploadWork(context: Context) {
        val constraint = Constraints.Builder()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            constraint.setRequiresDeviceIdle(true)
        }
        val req = OneTimeWorkRequestBuilder<UploadWorker>()
            .setConstraints(constraint.build())
            .setInitialDelay(UploadWorker.INITIAL_DELAY, TimeUnit.SECONDS)
            .setBackoffCriteria(
                BackoffPolicy.LINEAR,
                UploadWorker.BACKOFF_DELAY, TimeUnit.SECONDS
            ) // Result.retry()
            .setInputData(
                workDataOf(
                    UploadWorker.KEY_IMAGE_URL to UriUtils.getFileUri(
                        context,
                        context.cacheDir
                    ).path
                )
            )
            .addTag(UploadWorker.TAG)
            .build()
        WorkManager.getInstance(context).enqueue(req)
    }
}