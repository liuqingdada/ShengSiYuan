package com.shengsiyuan.coroutine.day02

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * Created by liuqing.yang
 * 2020/3/29.
 * Email: 1239604859@qq.com
 *
 * 使用async进行结构化并发程序开发
 */

fun main() = runBlocking {
    val elapsedTime = measureTimeMillis {
        println("intSum: ${intSum()}")
    }
    println("total time: $elapsedTime")
}

private suspend fun intSum(): Int = coroutineScope<Int> {
    val s1 = async { intValue1() }
    val s2 = async { intValue2() }
    s1.await() + s2.await()
}

private suspend fun intValue1(): Int {
    delay(2000)
    return 15
}

private suspend fun intValue2(): Int {
    delay(3000)
    return 20
}
