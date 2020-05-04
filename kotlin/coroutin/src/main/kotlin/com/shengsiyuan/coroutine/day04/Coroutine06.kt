package com.shengsiyuan.coroutine.day04

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import java.lang.Exception

/**
 * Created by liuqing.yang
 * 2020/5/4.
 * Email: 1239604859@qq.com
 *
 * 限定大小的中间操作
 */

private fun numbers(): Flow<Int> = flow {
    try {
        emit(1)
        emit(2)
        emit(3)
        println("Hello")
        emit(4)
    } catch (e: Exception) {
        println(e)
    } finally {
        println("finally")
    }
}

fun main() = runBlocking {
    numbers().take(3).collect {
        println(it)
    }
}