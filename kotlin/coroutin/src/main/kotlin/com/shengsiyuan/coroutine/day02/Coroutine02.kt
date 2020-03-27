package com.shengsiyuan.coroutine.day02

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * Created by liuqing.yang
 * 2020/3/27.
 * Email: 1239604859@qq.com
 *
 * 使用async与await实现并发
 *
 * 从概念上来说，async就像是launch一样，它会开启一个单独的协程，这个协程是个轻量级的线程，可以与其他协程并发工作。
 * 区别在于，launch会返回一个Job，但是Job并不会持有任何结果值，而async会返回一个Deferred，这是一个轻量级的非阻塞的
 * future，它代表一个promise，可以在稍后提供一个结果值
 *
 * 可以通在一个deferred值上调用.await()方法来获取最终的结果值，Defferred也是个Job因此可以在需要时对其进行取消
 */

fun main() = runBlocking {
    val elapsedTime = measureTimeMillis {
        val s1 = async { intValue1() }
        val s2 = async { intValue2() }

        val value1 = s1.await()
        val value2 = s2.await()

        println("$value1 + $value2 = ${value1 + value2}")
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