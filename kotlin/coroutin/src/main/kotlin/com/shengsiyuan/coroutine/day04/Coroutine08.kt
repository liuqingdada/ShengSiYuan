package com.shengsiyuan.coroutine.day04

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

/**
 * Created by liuqing.yang
 * 2020/5/4.
 * Email: 1239604859@qq.com
 *
 * Flow 是顺序执行的
 * 对于 Flow 的收集操作来说，它是运行在调用了终止操作的那个协程上，默认情况下，它是不会启动新的协成的。
 * 每个 emit 的元素值都会由所有的中间操作进行处理，最后再由终止操作进行处理。本质上，就是由上游进入到了下游
 *
 * Flow Context
 */

private fun log(msg: String) {
    println("[${Thread.currentThread().name}] - $msg")
}

private fun test(): Flow<Int> = flow {
    log("start")
    for (i in 1..3) {
        emit(i)
    }
}

private fun test2(): Flow<Int> = flow {
    withContext(Dispatchers.Default) {
        (1..4).forEach {
            Thread.sleep(1000)
            emit(it)
        }
    }
}

private fun test3(): Flow<Int> = flow {
    (1..4).forEach {
        Thread.sleep(100)
        log("emit: $it")
        emit(it)
    }
}.flowOn(Dispatchers.Default).onEach {
    log("onEach: $it")
}.flowOn(Dispatchers.IO)

fun main() = runBlocking {
    test().collect {
        log("collect: $it")
    }

    val time = measureTimeMillis {
        withContext(Dispatchers.Default) {
            test3()
//                    .buffer() // 如果是同一个协程，buffer 可以做缓冲；实际上，不同 flowOn 也是用到了 buffer
                    .collect {
                        delay(200)
                        log(it.toString())
                    }
        }
    }
    println(time)
}