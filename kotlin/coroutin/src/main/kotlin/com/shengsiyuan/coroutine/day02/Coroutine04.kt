package com.shengsiyuan.coroutine.day02

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * Created by liuqing.yang
 * 2020/3/29.
 * Email: 1239604859@qq.com
 *
 * 异步风格的函数
 */

fun main() {
    val elapsedTime = measureTimeMillis {
        val s1 = intValue1Async()
        val s2 = intValue2Async()

        runBlocking {
            val value1 = s1.await()
            val value2 = s2.await()
            println(value1 + value2)
        }
    }
    println("total time: $elapsedTime")
}

private suspend fun intValue1(): Int {
    delay(2000)
    return 15
}

private suspend fun intValue2(): Int {
    delay(3000)
    return 20
}

private fun intValue1Async() = GlobalScope.async {
    intValue1()
}

private fun intValue2Async() = GlobalScope.async {
    intValue2()
}