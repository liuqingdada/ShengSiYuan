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
//        testLog()
//        testANR()
    }

    private fun testWorker() {
        TaskManager.submitUploadWork(this, this)
        TaskManager.submitProgressWork(this, this)
        TaskManager.submitContinuationWorker(this)
        TaskManager.submitPeriodicWorker(this)

    }

    private fun testLog() {
        fun log(time: Int) {
            LogUtil.d(TAG, "testLog: ${Thread.currentThread().name}, time: $time")
        }

        val tpool = Executors.newFixedThreadPool(8)
        repeat(500) {
            log(it)
            tpool.execute { log(it) }
        }
    }

    private fun testANR() {
        a()
    }

    private fun a() {
        b()
        h()
        l()
        SystemClock.sleep(800)
    }

    private fun b() {
        c()
        g()
        SystemClock.sleep(200)
    }

    private fun c() {
        d()
        e()
        f()
        SystemClock.sleep(100)
    }

    private fun d() {
        SystemClock.sleep(20)
    }

    private fun e() {
        SystemClock.sleep(20)
    }

    private fun f() {
        SystemClock.sleep(20)
    }

    private fun g() {
        SystemClock.sleep(20)
    }

    private fun h() {
        SystemClock.sleep(20)
        i()
        j()
        k()
    }

    private fun i() {
        SystemClock.sleep(20)
    }

    private fun j() {
        SystemClock.sleep(6)
    }

    private fun k() {
        SystemClock.sleep(10)
    }

    private fun l() {
        SystemClock.sleep(10000)
    }
}
