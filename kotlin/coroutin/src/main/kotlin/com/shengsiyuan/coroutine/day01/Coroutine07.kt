package com.shengsiyuan.coroutine.day01

import kotlinx.coroutines.*

/**
 * Created by liuqing.yang
 * 2020/3/27.
 * Email: 1239604859@qq.com
 *
 * 我们在使用协程时，如果取消了协程，那么很大一部分原因都在于协程的执行时间超过了某个设定值；我们可以通过手工引用
 * 与协程对应的Job方式来启动一个单独的协程用于取消这个协程，不过Kotlin提供了一个内建的函数来帮助我们更好的做到
 *
 * 被取消的协程中，TimeoutCancellationException被认为是协程完成的正常原因，所有的资源都会以正常的方式来关闭，
 * 那么我们就可以将相关代码放到一个 try...catch 块中；此外对于 withTimeoutOrNull 函数不会抛这个异常，而是
 * 返回null
 */

fun main() = runBlocking {
    try {
        withTimeout(1900) {
            repeat(1000) {
                println("Hello, $it")
                delay(400)
            }
        }
    } catch (e: TimeoutCancellationException) {
        e.printStackTrace()
    }

    val ret = withTimeoutOrNull(1900) {
        repeat(1000) {
            println("Hello, $it")
            delay(400)
        }
        "Hello World"
    }
    println("The result is $ret")
}