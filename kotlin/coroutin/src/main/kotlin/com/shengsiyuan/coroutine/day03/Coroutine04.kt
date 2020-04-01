package com.shengsiyuan.coroutine.day03

import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/**
 * Created by andy
 * 2020/4/1.
 * Email: 1239604859@qq.com
 */

private fun log(msg: String) {
    println("[${Thread.currentThread().name}] - $msg")
}

fun main() {
    newSingleThreadContext("Context1").use { ctx1 ->
        newSingleThreadContext("Context2").use { ctx2 ->
            runBlocking(ctx1) {
                log("started in context1")

                withContext(ctx2) {
                    log("working in context2")
                }

                log("Back to context1")
            }
        }
    }
}