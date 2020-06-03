package com.android.cooper.app.workmanager.task

import android.content.Context
import android.os.Build
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.android.cooper.app.workmanager.utils.UriUtils
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Created by cooper
 * 20-6-2.
 * Email: 1239604859@qq.com
 */
object TaskManager {
    fun submitUploadWork(context: Context, lifecycleOwner: LifecycleOwner) {
        val constraint = Constraints.Builder()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            constraint.setRequiresDeviceIdle(false)
        }
        val tmpFile = File(context.cacheDir, "upload_work.tmp")
        if (!tmpFile.exists()) {
            tmpFile.writeText("Cooper app work manager test")
        }
        val req = OneTimeWorkRequestBuilder<UploadWorker>()
            .setConstraints(constraint.build())
            .setInitialDelay(UploadWorker.INITIAL_DELAY, TimeUnit.SECONDS)
            /*.setBackoffCriteria(
                BackoffPolicy.LINEAR,
                UploadWorker.BACKOFF_DELAY, TimeUnit.SECONDS
            ) // Result.retry(); Cannot set backoff criteria on an idle mode job*/
            .setInputData(
                workDataOf(
                    UploadWorker.KEY_IMAGE_URL to UriUtils.getFileUri(
                        context,
                        tmpFile
                    ).path
                )
            )
            .addTag(UploadWorker.TAG)
            .build()
        println("submitUploadWork: ${req.id}")
        WorkManager.getInstance(context)
            .getWorkInfoByIdLiveData(req.id)
            .observe(lifecycleOwner, Observer {
                it?.run { println(this) }
            })
        WorkManager.getInstance(context).enqueue(req)
    }

    fun submitProgressWork(context: Context, lifecycleOwner: LifecycleOwner) {
        val req = OneTimeWorkRequestBuilder<ProgressWorker>()
            .setInitialDelay(ProgressWorker.DELAY_DURARION, TimeUnit.MILLISECONDS)
            .addTag(ProgressWorker.TAG)
            .build()
        println("submitProgressWork: ${req.id}")
        WorkManager.getInstance(context)
            .getWorkInfoByIdLiveData(req.id)
            .observe(lifecycleOwner, Observer {
                it?.run { println(this) }
            })
        WorkManager.getInstance(context).enqueue(req)
    }
}