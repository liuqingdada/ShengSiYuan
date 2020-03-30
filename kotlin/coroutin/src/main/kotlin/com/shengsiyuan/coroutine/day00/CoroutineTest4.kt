package com.shengsiyuan.coroutine.day00

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Created by yangliuqing
 * 2019-08-19.
 * Email: 1239604859@qq.com
 */
fun main() = runBlocking {
    GlobalScope.launch {
        kotlinx.coroutines.delay(1000)
        println("Kotlin Coroutines")
    }

    println("Hello")

    kotlinx.coroutines.delay(2000)

    println("World")
}