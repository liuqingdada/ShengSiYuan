package com.shengsiyuan.coroutines.basic

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by andy
 * 2019-03-21.
 * Email: 1239604859@qq.com
 */

suspend fun main() {
    GlobalScope.launch {
        repeat(1000) {
            println("I'm sleeping $it ...")
            delay(500)
        }
    }
    delay(1300)
}