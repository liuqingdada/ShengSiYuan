package com.shengsiyuan.coroutine.day03

import kotlinx.coroutines.Job
import kotlinx.coroutines.isActive
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

/**
 * Created by andy
 * 2020/4/1.
 * Email: 1239604859@qq.com
 *
 * [Job]的使用方式以及在Context中的具体应用
 * 协程的[Job]是归属于[CoroutineContext]的一部分, Kotlin为我们提供了一种简洁的手段来通过协程上下文获取到
 * 协程自身的[Job]对象
 */

fun main() = runBlocking {
    val job: Job? = coroutineContext[Job]
    println(job)
    println(coroutineContext.isActive)
}
