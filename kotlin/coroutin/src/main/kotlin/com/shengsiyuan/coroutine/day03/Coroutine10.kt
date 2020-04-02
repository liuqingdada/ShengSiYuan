package com.shengsiyuan.coroutine.day03

import kotlinx.coroutines.*

/**
 * Created by liuqing.yang
 * 2020/4/2.
 * Email: 1239604859@qq.com
 */

class Activity : CoroutineScope by CoroutineScope(Dispatchers.Default) {

    fun onCreate() {
        requestUiData()
    }

    fun onDestroy() {
        cancel()
    }

    private fun requestUiData() {
        repeat(8) {
            launch {
                delay((it + 1) * 300L)
                println("coroutine $it has get server data")
            }
        }
    }
}

fun main() = runBlocking {
    val activity = Activity()

    println("AMS create activity")
    activity.onCreate()

    delay(1300L)
    println("AMS destroy activity")
    activity.onDestroy()

    delay(5000L)
}
