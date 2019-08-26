package com.shengsiyuan.coroutine

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Created by yangliuqing
 * 2019-08-26.
 * Email: 1239604859@qq.com
 *
 * 全局协程类似于守护线程（daemon thread）
 * 使用GlobalScope启动的活动协程并不会保持进程的生命，他们就像是守护线程一样
 */

fun main() {
//    execCoroutine()
    val service = execThread()

    Thread.sleep(2 * 1000)
    service.shutdownNow()
    println("main end")
}

fun execCoroutine() {
    GlobalScope.launch {
        repeat(100) {
            println("I am sleeping $it")

            delay(400)
        }
    }
}

fun execThread(): ExecutorService {
    val exe = Executors.newSingleThreadExecutor()
    exe.execute { execCoroutine() }
    return exe
}