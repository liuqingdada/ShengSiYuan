package com.shengsiyuan.coroutine.day04

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.runBlocking

/**
 * Created by liuqing.yang
 * 2020/5/4.
 * Email: 1239604859@qq.com
 *
 * 终止操作 Terminal Operation
 * Flow 的终止操作都是挂起函数，终止操作才会真正开始执行流的收集
 */

fun main() = runBlocking {
    val ret = (0..4).asFlow()
            .map { it * it }
            .reduce { s, t ->
                s + t
            }
    println(ret)
}