package com.shengsiyuan.coroutine

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Created by yangliuqing
 * 2019-08-25.
 * Email: 1239604859@qq.com
 *
 * 除去不同的协程构建器所提供的协程作用域（coroutine scope）外，我们还可以通过coroutineScope builder来声明自己的协程作用域。
 * 该构建器会创建一个协程作用域，并且会等待所有的子协程全部完成后自身才会完成。
 *
 * runBlocking与coroutineScope之间的主要差别在于，后者在等待所有子协程完成其任务时并不会阻塞当前的线程。
 *
 * 实际运行的时候，我目前觉着，也是阻塞了 ？？？
 */
fun main() = runBlocking {
    launch {
        delay(1000)
        println("my job1")
    }

    println("person")

    coroutineScope {
        launch {
            delay(20 * 1000)
            println("my job2")
        }

        delay(5 * 1000)
        println("hello world")
    }

    println("welcome")
}