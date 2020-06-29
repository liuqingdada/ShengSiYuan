package com.shengsiyuan.coroutine.day04

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import java.lang.RuntimeException

/**
 * Created by liuqing.yang
 * 2020/5/6.
 * Email: 1239604859@qq.com
 */

private fun test(): Flow<Int> = flow {
    repeat(4) {
        println("emit: $it")
        emit(it)
//        throw RuntimeException("up stream")
    }
}

fun main() = runBlocking {
    try {
        test().collect {
            println(it)
            check(it <= 1) {
                "Collected $it"
            }
        }
    } catch (e: Exception) {
        println(e)
    }
    println("=========================")

    test().catch {
        println(it)
    }.map {
        check(it <= 1) {
            "Collected $it"
        }
        "value -> $it"
    }.catch {
        println("catch $it")
    }.onCompletion {
        println("onComplete $it")
    }.collect {
        println(it)
    }
}