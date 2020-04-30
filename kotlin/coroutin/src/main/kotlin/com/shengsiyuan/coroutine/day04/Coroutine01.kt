package com.shengsiyuan.coroutine.day04

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

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

private fun flow2(): Flow<Int> = flow {
    for (i in 100..105) {
        delay(100)
        emit(i)
    }
}

fun main() {
    listMethod().forEach { println(it) }
    println("-----------")
    sequenceMethod().forEach { println(it) }
    println("-----------")

    runBlocking {
        flowMethod().forEach { println(it) }
        println("==============")

        launch {
            repeat(4) {
                println(it)
                delay(200)
            }
        }
        flow2().collect { println(it) }
    }
}




























