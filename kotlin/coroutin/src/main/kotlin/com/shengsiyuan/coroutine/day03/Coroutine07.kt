package com.shengsiyuan.coroutine.day03

import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Created by andy
 * 2020/4/1.
 * Email: 1239604859@qq.com
 *
 * 对于父子协程来说, 父协程总是会等待其所有子协程的完成
 * 父协程不比显式地去追踪它所启动的所有子协程, 同时也不必调用子协程的[Job.join]方法来等待子协程的完成
 */

fun main() = runBlocking {
    val request = launch {
        repeat(5) {
            launch {
                delay((it + 1) * 100L)
                println("Corotien $it finish")
            }
        }
        println("hello")
    }

    request.join()
    println("world")
}