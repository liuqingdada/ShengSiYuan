package com.shengsiyuan.coroutine.day03

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Created by liuqing.yang
 * 2020/4/2.
 * Email: 1239604859@qq.com
 */

fun main() = runBlocking<Unit>(CoroutineName("main")) {
    println("thread: ${Thread.currentThread().name}")

    launch(Dispatchers.Default + CoroutineName("HelloWorld")) {
        println("thread: ${Thread.currentThread().name}")
    }
}
