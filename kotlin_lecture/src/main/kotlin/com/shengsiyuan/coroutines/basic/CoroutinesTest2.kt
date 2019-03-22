package com.shengsiyuan.coroutines.basic

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Created by andy
 * 2019-03-21.
 * Email: 1239604859@qq.com
 */

/*
fun main() {
    GlobalScope.launch {
        delay(1000)
        println("world")
    }
    println("hello")
    runBlocking {
        delay(2000)
    }
    println("main end")
}
*/

// main 方法 和 JUnit 测试都可以这样来写
fun main() = runBlocking<Unit> {
    GlobalScope.launch {
        delay(1000)
        println("world")
    }
    println("hello")
    delay(2000)
}