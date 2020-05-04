package com.shengsiyuan.coroutine.day04

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking

/**
 * Created by liuqing.yang
 * 2020/5/4.
 * Email: 1239604859@qq.com
 *
 * Flow 的中间运算符
 *
 * Flow 与 Sequence 之间在中间运算符上的重要差别在于：对于 Flow 来说，这些中间运算符内的代码块是可以调用挂起函数的
 */

private suspend fun testExe(input: Int): String {
    delay(1000)
    return "output: $input"
}

private suspend fun map() {
    (0..10).asFlow()
            .map {
                testExe(it)
            }
            .collect {
                println(it)
            }
}

private suspend fun transform() {
    (0..10).asFlow()
            .transform {
                emit("input: $it")
                emit(testExe(it))
                emit("Hello World")
            }
            .collect {
                println(it)
            }
}

fun main() = runBlocking {
    map()
    println("------------------------")
    transform()
}
