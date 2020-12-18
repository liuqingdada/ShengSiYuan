package com.android.cooper.app.workmanager

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.work.ListenableWorker
import androidx.work.testing.TestListenableWorkerBuilder
import com.android.cooper.app.workmanager.task.ProgressWorker
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by cooper
 * 20-6-11.
 * Email: 1239604859@qq.com
 */
@RunWith(AndroidJUnit4::class)
class WorkerTest {
    companion object {
        private const val TAG = "WorkerTest"
    }

    private lateinit var context: Context

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun testProgressWorker() {
        val worker = TestListenableWorkerBuilder<ProgressWorker>(context).build()
        runBlocking {
            val result = worker.doWork()
            assertThat(result is (ListenableWorker.Result.Success))
        }
    }
}