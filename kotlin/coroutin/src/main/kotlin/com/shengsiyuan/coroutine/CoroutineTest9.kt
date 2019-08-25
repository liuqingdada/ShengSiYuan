package com.shengsiyuan.coroutine

import kotlin.concurrent.thread

/**
 * Created by yangliuqing
 * 2019-08-25.
 * Email: 1239604859@qq.com
 */
fun main() {
    repeat(10000) {
        thread {
            Thread.sleep(1000)
            println("A")
        }
    }

    println("Hello World")
}