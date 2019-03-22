package com.shengsiyuan.coroutines.cancel.timeout

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Created by andy
 * 2019-03-21.
 * Email: 1239604859@qq.com
 */

fun main() = runBlocking {
    val job = launch {
        try {

            repeat(1000) {
                println(it)
                delay(500)
            }
        } finally {
            println("running finally")
        }
    }
    delay(1300)
    println("main: tired waiting")
    job.cancelAndJoin()
    println("main: quite")
}