package com.shengsiyuan.coroutine

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Created by yangliuqing
 * 2019-08-25.
 * Email: 1239604859@qq.com
 *
 * 协程是轻量级的
 */

fun main() = runBlocking {
    repeat(10000) {
        launch {
            delay(1000)
            println("A")
        }
    }

    println("Hello World")
}