package com.shengsiyuan.coroutine.day04

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

/**
 * Created by liuqing.yang
 * 2020/5/4.
 * Email: 1239604859@qq.com
 *
 * FLow 的取消
 */

private fun testCancel(): Flow<Int> = flow {
    repeat(4) {
        delay(100)
        println("Emit $it")
        emit(it)
    }
}

fun main() = runBlocking {
    withTimeoutOrNull(280) {
        testCancel().collect {
            println(it)
        }
    }
    println("finished")
}