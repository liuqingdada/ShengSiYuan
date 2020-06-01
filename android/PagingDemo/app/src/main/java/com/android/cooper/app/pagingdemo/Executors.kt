package com.android.cooper.app.pagingdemo

import android.os.Handler
import android.os.Looper
import java.util.*
import java.util.concurrent.*

/**
 * Created by cooper
 * 20-6-1.
 * Email: 1239604859@qq.com
 */
class SerialExecutor : Executor {
    private val tasks = ArrayDeque<Runnable>()
    private var active: Runnable? = null

    @Synchronized
    override fun execute(r: Runnable) {
        tasks.offer(Runnable {
            try {
                r.run()
            } finally {
                scheduleNext()
            }
        })
        if (active == null) {
            scheduleNext()
        }
    }

    private fun scheduleNext() {
        active = tasks.poll()
        active?.run {
            serialPoolExecutor.execute(this)
        }
    }

    companion object {
        private const val CORE_POOL_SIZE = 1
        private const val MAXIMUM_POOL_SIZE = 20
        private const val BACKUP_POOL_SIZE = 5
        private const val KEEP_ALIVE_SECONDS: Long = 3

        // Used only for rejected executions.
        // Initialization protected by sRunOnSerialPolicy lock.
        private var backupExecutor: ThreadPoolExecutor? = null
        private var backupExecutorQueue: LinkedBlockingQueue<Runnable>? = null

        private var serialPoolExecutor: ThreadPoolExecutor = ThreadPoolExecutor(
            CORE_POOL_SIZE,
            MAXIMUM_POOL_SIZE,
            KEEP_ALIVE_SECONDS,
            TimeUnit.SECONDS,
            SynchronousQueue(),
            SerialPolicy()
        )
    }

    class SerialPolicy : RejectedExecutionHandler {
        override fun rejectedExecution(r: Runnable, executor: ThreadPoolExecutor) {
            // As a last ditch fallback, run it on an executor with an unbounded queue.
            // Create this executor lazily, hopefully almost never.
            synchronized(this) {
                if (backupExecutor == null) {
                    backupExecutorQueue = LinkedBlockingQueue()
                    backupExecutor = ThreadPoolExecutor(
                        BACKUP_POOL_SIZE,
                        BACKUP_POOL_SIZE,
                        KEEP_ALIVE_SECONDS,
                        TimeUnit.SECONDS,
                        backupExecutorQueue
                    )
                    backupExecutor?.allowCoreThreadTimeOut(true)
                }
            }
            backupExecutor?.execute(r)
        }
    }
}

private val SERIAL_EXECUTOR = SerialExecutor()

private val MAIN_HANDLER = Handler(Looper.getMainLooper())

fun serialExecute(block: () -> Unit) {
    SERIAL_EXECUTOR.execute(block)
}

fun mainThread(block: () -> Unit) {
    MAIN_HANDLER.post(block)
}