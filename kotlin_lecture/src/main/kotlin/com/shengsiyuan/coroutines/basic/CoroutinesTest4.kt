package com.shengsiyuan.coroutines.basic

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Created by andy
 * 2019-03-21.
 * Email: 1239604859@qq.com
 */

@Suppress("BlockingMethodInNonBlockingContext")
suspend fun main() = runBlocking<Unit> {
    launch {
        delay(1000)
        println("world")
    }
    println("hello")

    coroutineScope {
        launch {
            delay(5000)
            println("task from nested launch")
        }
        delay(2000)
        println("task from coroutineScope")
    }

    println("main end")
}