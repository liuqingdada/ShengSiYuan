package com.shengsiyuan.coroutines.basic

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Created by andy
 * 2019-03-21.
 * Email: 1239604859@qq.com
 */

fun main() = runBlocking {
    launch {
        doWorld()
    }
    println("hello")
}

suspend fun doWorld() {
    delay(1000)
    println("world")
}