package com.android.cooper.app.workmanager

import android.content.Context
import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.work.*
import androidx.work.testing.SynchronousExecutor
import androidx.work.testing.WorkManagerTestInitHelper
import com.android.cooper.app.workmanager.task.EchoWorker
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by cooper
 * 20-6-11.
 * Email: 1239604859@qq.com
 */
@RunWith(AndroidJUnit4::class)
class BasicInstrumentationTest {
    companion object {
        private const val KEY1 = "key1"
        private const val KEY2 = "key2"
    }

    private lateinit var context: Context

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        val config = Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)
            .setExecutor(SynchronousExecutor())
            .build()
        WorkManagerTestInitHelper.initializeTestWorkManager(context, config)
    }

    @Test
    @Throws(Exception::class)
    fun testSimpleEchoWorker() {
        val input = workDataOf(KEY1 to 1, KEY2 to 2)
        val req = OneTimeWorkRequestBuilder<EchoWorker>()
            .setInputData(input)
            .build()
        val workManager = WorkManager.getInstance(context)
        // Enqueue and wait for result. This also runs the Worker synchronously
        // because we are using a SynchronousExecutor.
        workManager.enqueue(req).result.get()
        // Get WorkInfo and outputData
        val workInfo = workManager.getWorkInfoById(req.id).get()
        val outputData = workInfo.outputData

        assertThat(workInfo.state == WorkInfo.State.SUCCEEDED)
        assertThat(outputData == input)
    }

    @Test
    @Throws(Exception::class)
    fun testEchoWorkerNoInput() {
        val req = OneTimeWorkRequestBuilder<EchoWorker>().build()
        val workManager = WorkManager.getInstance(context)
        // Enqueue and wait for result. This also runs the Worker synchronously
        // because we are using a SynchronousExecutor.
        workManager.enqueue(req).result.get()
        // Get WorkInfo and outputData
        val workInfo = workManager.getWorkInfoById(req.id).get()

        assertThat(workInfo.state == WorkInfo.State.FAILED)
    }
}