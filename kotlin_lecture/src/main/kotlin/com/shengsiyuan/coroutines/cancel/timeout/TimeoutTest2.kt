package com.shengsiyuan.coroutines.cancel.timeout

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

/**
 * Created by andy
 * 2019-03-21.
 * Email: 1239604859@qq.com
 */

fun main() = runBlocking {
    val ret = withTimeoutOrNull(1300) {
        repeat(100) {
            println(it)
            delay(500)
        }
        "Done"
    }

    if (ret == null) {
        println("running failed")
    } else {
        println("running success")
    }
}