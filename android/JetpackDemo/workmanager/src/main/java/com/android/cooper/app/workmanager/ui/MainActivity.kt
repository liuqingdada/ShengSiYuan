package com.android.cooper.app.workmanager.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import com.android.cooper.app.workmanager.R
import com.android.cooper.app.workmanager.task.TaskManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        TaskManager.submitUploadWork(this, this)
        TaskManager.submitProgressWork(this, this)
        TaskManager.submitContinuationWorker(this)
        TaskManager.submitPeriodicWorker(this)
    }
}
