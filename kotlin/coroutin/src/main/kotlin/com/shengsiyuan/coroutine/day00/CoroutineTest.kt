package com.shengsiyuan.coroutine.day00

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by yangliuqing
 * 2019-08-11.
 * Email: 1239604859@qq.com
 */
fun main() {
    GlobalScope.launch {
        delay(1000)
        println("Kotlin Coroutine: ${Thread.currentThread().name}")
    }

    println("Hello: ${Thread.currentThread().name}")

    Thread.sleep(2000)

    println("World: ${Thread.currentThread().name}")
}