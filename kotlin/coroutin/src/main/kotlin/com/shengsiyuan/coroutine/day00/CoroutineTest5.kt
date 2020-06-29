package com.shengsiyuan.coroutine.day00

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Created by yangliuqing
 * 2019-08-19.
 * Email: 1239604859@qq.com
 */

// runBlocking 作用域
fun main() = runBlocking {
    // GlobalScope.launch 作用域
    val job: Job = GlobalScope.launch {
        kotlinx.coroutines.delay(1000)
        println("Kotlin Coroutines")
    }

    println("Hello")

    // 同一作用域下, 所有启动的协程全部完成后才会完成
    // 但是, 此处是不同的作用域, 所以一定要 join
    job.join()

    println("World")
}