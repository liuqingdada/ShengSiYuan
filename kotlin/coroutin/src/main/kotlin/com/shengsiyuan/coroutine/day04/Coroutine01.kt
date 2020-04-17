package com.shengsiyuan.coroutine.day04

import kotlinx.coroutines.delay

/**
 * 方法本身是阻塞的
 * 集合本身是一次性返回给调用端的，即集合中的全部元素均已经获取到后才一同返回给调用端
 */
private fun listMethod() = arrayListOf<String>("hello", "world", "hello world")

/**
 * Sequence 序列
 * 如果在获取每一个元素时都需要执行一定的计算，这种计算是一种阻塞行为，将计算后的多个结果返回给调用端
 * 阻塞线程的执行
 */
private fun sequenceMethod(): Sequence<Int> = sequence {
    for (i in 100..105) {
        Thread.sleep(1000)
        yield(i)
    }
}

private suspend fun flowMethod(): List<String> {
    delay(1000)

    return arrayListOf("Hello", "World", "Hello World")
}

fun main() {
    listMethod().forEach { println(it) }
}




























