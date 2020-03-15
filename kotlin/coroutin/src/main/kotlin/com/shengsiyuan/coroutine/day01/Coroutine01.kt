package com.shengsiyuan.coroutine.day01

import kotlinx.coroutines.*

/**
 * Created by liuqing.yang
 * 2020/3/15.
 * Email: 1239604859@qq.com
 *
 * 协成的取消与超时
 */

fun main() = runBlocking {
    val job = GlobalScope.launch {
        repeat(200) {
            println("hello: $it")
            delay(500)
        }
    }

    delay(1100)
    println("Hello World")

//    job.cancel()
//    job.join()
    job.cancelAndJoin()

    println("welcome")
}
