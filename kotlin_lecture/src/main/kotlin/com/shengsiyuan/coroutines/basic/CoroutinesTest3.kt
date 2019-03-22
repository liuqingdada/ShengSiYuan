package com.shengsiyuan.coroutines.basic

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by andy
 * 2019-03-21.
 * Email: 1239604859@qq.com
 */

suspend fun main() {
    val job = GlobalScope.launch {
        delay(1000)
        println("world")
    }
    println("hello")
    job.join()
}