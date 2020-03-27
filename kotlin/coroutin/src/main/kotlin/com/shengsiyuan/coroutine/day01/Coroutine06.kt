package com.shengsiyuan.coroutine.day01

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

/**
 * Created by liuqing.yang
 * 2020/3/17.
 * Email: 1239604859@qq.com
 *
 * 我们在使用协程的，如果取消了协程，那么很大一部分原因都在于协程的执行时间超过了某个限定值；我们可以通过手工引用与
 * 协程对应的Job的方式来启动另一个单独的协程来取消这个协程，不过kotlin提供了一个内建的函数来帮助我们又快又好的
 * 做到这一点
 */

fun main() = runBlocking {
    withTimeout(1900) {
        repeat(1000) {
            println("Hello: $it")
            delay(400)
        }
    }
}