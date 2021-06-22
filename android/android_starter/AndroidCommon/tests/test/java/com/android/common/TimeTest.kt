package com.android.common

import kotlinx.coroutines.*
import org.junit.Test

/**
 * Created by cooper
 * 2021/5/8.
 * Email: 1239604859@qq.com
 */
class TimeTest {
    @Test
    fun timeoutTest() = runBlocking {
        val job = timeout(this, 3000, {
            println("start time")
        }, {
            println("time out")
        })
    }

    @Test
    fun timeoutCancelTest() = runBlocking {
        val scop = MainScope() // in android use
        val job = timeout(scop, 5000, {
            while (isActive) {
                delay(300)
                println("start time")
            }
        }, {
            println("time out")
        })
        delay(1000)
        job.cancel()  // 只取消自己
        // scop.cancel() // 取消所有的任务
    }

    private fun timeout(
        scope: CoroutineScope,
        delayMillis: Long,
        before: suspend () -> Unit,
        after: suspend () -> Unit
    ): Job {
        return scope.launch(Dispatchers.Default) {
            before()
            delay(delayMillis)
            after()
        }
    }
}