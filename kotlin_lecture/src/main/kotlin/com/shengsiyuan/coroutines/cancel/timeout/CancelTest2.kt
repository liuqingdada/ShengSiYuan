package com.shengsiyuan.coroutines.cancel.timeout

import kotlinx.coroutines.*

/**
 * Created by andy
 * 2019-03-21.
 * Email: 1239604859@qq.com
 */

fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
//        while (i < 5) { // 一个执行计算的循环，只是为了占用 CPU
//            yield()
        while (isActive) { // 可以被取消的计算循环
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("I'm sleeping ${i++}")
                nextPrintTime += 500
            }
        }
    }
    delay(1300)
    println("main: I'm tired of waiting")
    job.cancelAndJoin() // 取消一个任务并且等待它结束
    println("main: Now I can quite")
}