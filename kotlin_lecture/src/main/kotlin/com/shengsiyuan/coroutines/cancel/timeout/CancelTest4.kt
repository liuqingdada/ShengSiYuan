package com.shengsiyuan.coroutines.cancel.timeout

import kotlinx.coroutines.*

/**
 * Created by andy
 * 2019-03-21.
 * Email: 1239604859@qq.com
 */
fun main() = runBlocking {
    val job = launch {
        try {
            repeat(20) {
                println(it)
                delay(500)
            }
        } finally {
            withContext(NonCancellable) {
                println("running finally")
                delay(1000)
                println("And I've just delayed for 1 sec because I'm non-cancellable")
            }
        }
    }
    delay(1300)
    println("main: tired of waiting")
    job.cancelAndJoin()
    println("main: quite")
}