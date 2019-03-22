package com.shengsiyuan.coroutines.cancel.timeout

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

/**
 * Created by andy
 * 2019-03-21.
 * Email: 1239604859@qq.com
 */

fun main() = runBlocking {
    withTimeout(1300) {
        repeat(100) {
            println(it)
            delay(500)
        }
    }
}