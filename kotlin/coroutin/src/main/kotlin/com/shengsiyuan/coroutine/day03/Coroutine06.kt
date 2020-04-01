package com.shengsiyuan.coroutine.day03

import kotlinx.coroutines.*

/**
 * Created by andy
 * 2020/4/1.
 * Email: 1239604859@qq.com
 *
 * 当一个协程是通过另一个协程的[CoroutineScope]来启动的, 那么这个协程就会通过[CoroutineScope.coroutineContext]
 * 来继承其上下文信息, 同时, 新协程的[Job]就会成为父协程[Job]的一个孩子
 * 当父协程被取消执行时, 该父协程的所有孩子都会通过递归的方式一并取消执行
 *
 * 特例情况: 当我们使用[GlobalScope]来启动协程时, 对于启动的新协程来说, 其[Job]是没有父[Job]的, 因此, 他就不会绑定
 * 到其所启动的那个范围上, 故其可以独立执行
 */

fun main() = runBlocking {
    val request = launch {
        GlobalScope.launch {
            println("job1: hello")
            delay(1000)
            println("job1: world")
        }

        launch {
            delay(100)
            println("job2: hello")
            delay(1000)
            println("job2: world")
        }
    }
    delay(500)
    request.cancelAndJoin()

    delay(1000)
    println("welcome")
}