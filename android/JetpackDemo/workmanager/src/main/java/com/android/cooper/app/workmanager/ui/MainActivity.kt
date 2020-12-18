package com.android.cooper.app.workmanager.ui

import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import com.android.common.utils.LogUtil
import com.android.cooper.app.workmanager.R
import com.android.cooper.app.workmanager.task.TaskManager
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        testWorker()
    }

    private fun testWorker() {
        TaskManager.submitUploadWork(this, this)
        TaskManager.submitProgressWork(this, this)
        TaskManager.submitContinuationWorker(this)
        TaskManager.submitPeriodicWorker(this)

    }
}
