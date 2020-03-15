package com.shengsiyuan.coroutine.day01

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Created by liuqing.yang
 * 2020/3/15.
 * Email: 1239604859@qq.com
 *
 * 使用finally来关闭资源
 *
 * job的join和cancelAndJoin都会等待所有清理动作完成才会继续往下执行
 *
 */

fun main() = runBlocking {
    val job = launch {
        try {
            repeat(100) {
                println("job repeat $it")
                delay(500)
            }
        } finally {
            println("execute finally")
        }
    }

    delay(1300)
    println("hello")

    job.cancelAndJoin()
    println("world")
}

