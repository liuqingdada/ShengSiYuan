package com.android.cooper.app.workmanager.task

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.delay

/**
 * Created by cooper
 * 20-6-3.
 * Email: 1239604859@qq.com
 */
class ProgressWorker(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params) {
    companion object {
        const val TAG = "ProgressWorker"
        const val DELAY_DURARION = 1000L
    }

    override suspend fun doWork(): Result {
        val firstUpdate = workDataOf(TAG to 0)
        val endUpdate = workDataOf(TAG to 100)
        setProgress(firstUpdate)
        delay(DELAY_DURARION)
        setProgress(endUpdate)
        return Result.success()
    }
}