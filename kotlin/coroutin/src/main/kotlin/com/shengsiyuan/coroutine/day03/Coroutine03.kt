package com.shengsiyuan.coroutine.day03

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * Created by andy
 * 2020/4/1.
 * Email: 1239604859@qq.com
 *
 * 使用JVM参数:
 * -Dkotlinx.coroutines.debug
 */

private fun log(msg: String) {
    println("[${Thread.currentThread().name}] - $msg")
}

fun main() = runBlocking {
    val s1 = async {
        delay(2000)
        log("hello world")
        10
    }

    val s2 = async {
        log("welcome")
        20
    }

    log("The result is ${s1.await() + s2.await()}")
}