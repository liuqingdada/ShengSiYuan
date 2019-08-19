package com.shengsiyuan.coroutine

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Created by yangliuqing
 * 2019-08-19.
 * Email: 1239604859@qq.com
 */
fun main() {
    GlobalScope.launch {
        delay(1000)
        println("Kotlin Coroutines")
    }

    println("Hello")

    runBlocking {
        delay(2000)
    }

    println("World")
}