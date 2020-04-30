package com.shengsiyuan.coroutine.day04

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

/**
 * Created by andy
 * 2020/4/28.
 * Email: 1239604859@qq.com
 *
 * [Flow] 中间操作, 终止操作
 * By default, flows are sequential and all flow operations are executed sequentially
 * in the same coroutine
 */

private fun flowTest(flag: String): Flow<String> = flow {
    println("flow test run")
    repeat(4) {
        delay(100)
        emit("$it - $flag")
    }
}

fun main() = runBlocking {
    println("hello")
    val flow = flowTest("test1")
    println("world")

    flow.collect { println(it) }
    flowTest("test2").collect {
        println(it)
    }

    withTimeoutOrNull(280) {
        flow.collect { println(it) }
    }

    println("finished")
}