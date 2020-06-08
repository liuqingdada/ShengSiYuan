package com.android.cooper.app.workmanager.task

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.android.app.common.utils.LogUtil

/**
 * Created by cooper
 * 20-6-2.
 * Email: 1239604859@qq.com
 */
class UploadWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : Worker(appContext, workerParams) {

    companion object {
        const val INITIAL_DELAY: Long = 10
        const val BACKOFF_DELAY: Long = 60

        const val TAG = "UploadWorker"
        const val KEY_IMAGE_URL = "key_image_url"
    }

    /**
     * 按照设计，Data 对象应该很小，值可以是字符串、基元类型或数组变体。
     * 如果需要将更多数据传入和传出工作器，应该将数据放在其他位置，
     * 例如 Room 数据库。Data 对象的大小上限为 10KB。
     */
    override fun doWork(): Result {
        val imageUri = inputData.getString(KEY_IMAGE_URL)
        val resp = uploadImage(imageUri)
        val outputData = workDataOf(KEY_IMAGE_URL to resp)
        return Result.success(outputData)
    }

    private fun uploadImage(uri: String?): String {
        LogUtil.d(TAG, Thread.currentThread().name)
        LogUtil.d(TAG, "upload images...")
        Thread.sleep(1000)
        return "{\"code\":200,\"msg\":\"操作成功\"}"
    }
}