package com.shengsiyuan.coroutine.day04

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.runBlocking

/**
 * Created by liuqing.yang
 * 2020/5/6.
 * Email: 1239604859@qq.com
 *
 * onCompletion catch 只能看到它的上游的异常，看不到下游的异常
 */

fun main() = runBlocking {
    try {
        (0..10).asFlow().collect { println(it) }
    } finally {
        println("onCompleted")
    }

    flow {
        emit(1)
        throw RuntimeException()
    }.onCompletion {
        if (it == null) {
            println("onCompleted")
        } else {
            println("Flow Completed Exceptionally")
        }
    }.collect {
        println(it)
    }
}