package com.android.cooper.app.workmanager.task

import android.annotation.SuppressLint
import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * Created by cooper
 * 20-6-11.
 * Email: 1239604859@qq.com
 */
class EchoWorker(context: Context, parameters: WorkerParameters) : Worker(context, parameters) {
    @SuppressLint("RestrictedApi")
    override fun doWork(): Result {
        return when (inputData.size()) {
            0 -> Result.failure()
            else -> Result.success()
        }
    }
}