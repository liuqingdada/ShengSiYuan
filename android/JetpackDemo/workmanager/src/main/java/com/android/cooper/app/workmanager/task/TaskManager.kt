package com.android.cooper.app.workmanager.task

import android.content.Context
import android.os.Build
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.work.*
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

    /**
     * 创建 OneTimeWorkRequest 链时，需要注意以下几点：
     * 从属 OneTimeWorkRequest 仅在其所有父级 OneTimeWorkRequest 都成功完成（即返回 Result.success()）
     * 时才会被解除阻塞（变为 ENQUEUED 状态）。
     *
     * 如果有任何父级 OneTimeWorkRequest 失败（返回 Result.failure()），则所有从属 OneTimeWorkRequest
     * 也会被标记为 FAILED。
     *
     * 如果有任何父级 OneTimeWorkRequest 被取消，则所有从属 OneTimeWorkRequest 也会被标记为 CANCELLED。
     * @param context Context
     */
    fun submitContinuationWorker(context: Context) {
        val req1 = OneTimeWorkRequestBuilder<ProgressWorker>()
            .setInputMerger(ArrayCreatingInputMerger::class)
            .setInitialDelay(ProgressWorker.DELAY_DURARION, TimeUnit.MILLISECONDS)
            .addTag(ProgressWorker.TAG)
            .build()
        val req2 = OneTimeWorkRequestBuilder<ProgressWorker>()
            .setInputMerger(ArrayCreatingInputMerger::class)
            .setInitialDelay(ProgressWorker.DELAY_DURARION, TimeUnit.MILLISECONDS)
            .addTag(ProgressWorker.TAG)
            .build()
        val req3 = OneTimeWorkRequestBuilder<ProgressWorker>()
            .setInputMerger(ArrayCreatingInputMerger::class)
            .setInitialDelay(ProgressWorker.DELAY_DURARION, TimeUnit.MILLISECONDS)
            .addTag(ProgressWorker.TAG)
            .build()
        val compress = OneTimeWorkRequestBuilder<UploadWorker>()
            .setInputMerger(ArrayCreatingInputMerger::class)
            .setInitialDelay(UploadWorker.INITIAL_DELAY, TimeUnit.MILLISECONDS)
            .addTag(UploadWorker.TAG)
            .build()
        val upload = OneTimeWorkRequestBuilder<UploadWorker>()
            .setInputMerger(ArrayCreatingInputMerger::class)
            .setInitialDelay(UploadWorker.INITIAL_DELAY, TimeUnit.MILLISECONDS)
            .addTag(UploadWorker.TAG)
            .build()
        WorkManager.getInstance(context)
            .beginWith(listOf(req1, req2, req3))
            .then(compress)
            .then(upload)
            .enqueue()
    }

    fun submitPeriodicWorker(context: Context) {
        val req = PeriodicWorkRequestBuilder<UploadWorker>(20L, TimeUnit.SECONDS)
            .setInitialDelay(UploadWorker.INITIAL_DELAY, TimeUnit.SECONDS)
            .build()
        println("submitPeriodicWorker: ${req.id}")
        WorkManager.getInstance(context).enqueue(req)
    }
}