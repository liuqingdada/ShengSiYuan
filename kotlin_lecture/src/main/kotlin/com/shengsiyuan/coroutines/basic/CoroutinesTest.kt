package com.shengsiyuan.coroutines.basic

import kotlinx.coroutines.*

/**
 * Created by andy
 * 2019-03-21.
 * Email: 1239604859@qq.com
 */

@ExperimentalCoroutinesApi
suspend fun main() {
    GlobalScope.launch {
        delay(1000L)
        println("World!")
    }

    println("hello,")

    println("===========")

    val ret = GlobalScope.async {
        delay(1000)
        return@async "123"
    }
    ret.await()
    println(ret.getCompleted())
}