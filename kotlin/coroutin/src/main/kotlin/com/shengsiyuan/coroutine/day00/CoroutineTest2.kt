package com.shengsiyuan.coroutine.day00

import kotlin.concurrent.thread

/**
 * Created by yangliuqing
 * 2019-08-11.
 * Email: 1239604859@qq.com
 */
fun main() {
    thread {
        Thread.sleep(1000)
        println("Kotlin c thread: ${Thread.currentThread().name}")
    }

    println("Hello")

    Thread.sleep(2000)

    println("World")
}