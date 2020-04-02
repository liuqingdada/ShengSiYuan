package com.shengsiyuan.coroutine.day03

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * Created by andy
 * 2020/4/1.
 * Email: 1239604859@qq.com
 * [CoroutineName]上下文元素可以让我们对协程进行命名, 以便能够输出可读性较好的日志信息
 */

private fun log(msg: String) {
    println("[${Thread.currentThread().name}] - $msg")
}

fun main() = runBlocking(CoroutineName("main")) {
    log("hello")
    val s1 = async(CoroutineName("coroutine1")) {
        delay(800)
        log("coroutine1 log")
        30
    }
    val s2 = async(CoroutineName("coroutine2")) {
        delay(1000)
        log("coroutine2 log")
        5
    }

    log("result is ${s1.await() + s2.await()}")
}
