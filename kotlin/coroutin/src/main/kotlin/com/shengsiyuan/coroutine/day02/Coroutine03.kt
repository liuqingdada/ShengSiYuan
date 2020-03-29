package com.shengsiyuan.coroutine.day02

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext
import kotlin.system.measureTimeMillis

/**
 * Created by liuqing.yang
 * 2020/3/29.
 * Email: 1239604859@qq.com
 *
 * 关于async的延迟执行
 * 我么可以通过将async方法的start参数设置为CoroutineStart.LAZY来实现协程的延迟执行
 * 在这种情况下，协程会在两种场景下去执行：
 * 1. 调用Deferred的await方法
 * 2. 调用Job的start方法
 */

fun main() = runBlocking {
    val elapsedTime = measureTimeMillis {
        val s1 = async(start = CoroutineStart.LAZY) {
            intValue1()
        }
        val s2 = async(start = CoroutineStart.LAZY) {
            intValue2()
        }
        println("hello world")
        Thread.sleep(2500)
        delay(2500)

//        s1.start()
//        s2.start()

        val value1 = s1.await()
        val value2 = s2.await()

        println(value1 + value2)
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