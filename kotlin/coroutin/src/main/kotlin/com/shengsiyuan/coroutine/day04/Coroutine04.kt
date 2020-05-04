package com.shengsiyuan.coroutine.day04

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

/**
 * Created by liuqing.yang
 * 2020/5/4.
 * Email: 1239604859@qq.com
 *
 * Flow 的构建
 * flow、flowOf、asFlow
 *
 * crossinline 背标记的 lambda 表达式是不允许非局部返回的
 */

fun main() = runBlocking {
    (0..10).asFlow().collect {
        println(it)
    }

    flowOf(10, 20, 30, 40).collect {
        println(it)
    }
}
