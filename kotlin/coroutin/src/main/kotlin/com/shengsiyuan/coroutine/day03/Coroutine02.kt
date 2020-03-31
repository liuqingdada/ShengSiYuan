package com.shengsiyuan.coroutine.day03

import kotlinx.coroutines.*

/**
 * Created by liuqing.yang
 * 2020/3/31.
 * Email: 1239604859@qq.com
 *
 * Dispatchers.Unconfined协程分发器会在调用者线程中去启动协程，但仅仅会持续到第一个挂起点
 * 当挂起函数结束后程序恢复执行时，它会继续协程的代码执行，但这时执行协程的线程是由之前所调用的挂起函数来决定的
 * Dispatchers.Unconfined协程分发器适用于这样的一些协程：
 * 它既不会消耗CPU时间，同时也不会更新任何共享的数据(特定于具体的线程)
 *
 * Dispatchers.Unconfined是一种高级的机制，他对于某些特殊情况是很有帮助作用的：协程执行的分发是不需要的，或者
 * 会产生意料之外的副作用，这是因为协程中的操作必须要立刻执行
 */

fun main() = runBlocking<Unit> {
    launch(Dispatchers.Unconfined) {
        println("unconfined, thread: ${Thread.currentThread().name}")
        delay(300)
        println("unconfined, thread: ${Thread.currentThread().name}")
    }

    launch {
        println("no param, thread: ${Thread.currentThread().name}")
        delay(2000)
        println("no param, thread: ${Thread.currentThread().name}")
    }
}
