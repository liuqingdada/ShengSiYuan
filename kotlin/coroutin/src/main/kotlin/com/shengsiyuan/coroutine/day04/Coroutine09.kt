package com.shengsiyuan.coroutine.day04

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

/**
 * Created by liuqing.yang
 * 2020/5/6.
 * Email: 1239604859@qq.com
 *
 * Flow 的组合
 */

fun main() = runBlocking {
    val nums = (1..5).asFlow()
    val strs = flowOf("one", "two", "three", "four", "five")

    nums.zip(strs) { a, b ->
        "$a -> $b"
    }.collect {
        println(it)
    }

    println("----------------------------------")

    nums.onEach {
        delay(100)
    }.flatMapConcat {
        flow {
            emit("$it, first")
            delay(500)
            emit("$it, second")
        }
    }.collect {
        println(it)
    }
}