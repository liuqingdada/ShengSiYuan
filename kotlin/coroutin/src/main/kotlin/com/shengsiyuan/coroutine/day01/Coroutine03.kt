package com.shengsiyuan.coroutine.day01

import kotlinx.coroutines.*

/**
 * Created by liuqing.yang
 * 2020/3/15.
 * Email: 1239604859@qq.com
 *
 * 有两种方式可以让计算代码变为可取消的：
 * 1. 周期性的调用一个挂起函数，该挂起函数会检查取消状态，比如说使用yield函数
 * 2. 显示地检查取消状态
 */

fun main() = runBlocking {
    val startTime = System.currentTimeMillis()

    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime

        var i = 0

        /*while (i < 20) { // 方式1
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("job: I am sleeping ${i++}")
                nextPrintTime += 500L
            }
            yield()
        }*/

        while (isActive) { // 方式2
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
