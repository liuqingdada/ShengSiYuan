package com.shengsiyuan.coroutine.day01

import kotlinx.coroutines.*

/**
 * Created by liuqing.yang
 * 2020/3/15.
 * Email: 1239604859@qq.com
 *
 * kotlinx.coroutines包下的所有挂起函数都是可取消的
 *
 * 他们会检查协成的取消状态，当取消时就会抛出CancellationException
 *
 * 不过，如果协成正在处于某个计算过程中，并且没有检查取消状态，那么他是无法被取消的
 */

fun main() = runBlocking {
    val startTime = System.currentTimeMillis()

    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime

        var i = 0

        while (i < 20) { // 此处就是处于计算过程中，而且没有检查取消状态，无法取消
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("job: I am sleeping ${i++}")
                nextPrintTime += 500L
            }
        }
    }

    delay(1300)
    println("hello wworld")

    job.cancelAndJoin()
    println("welcome")
}