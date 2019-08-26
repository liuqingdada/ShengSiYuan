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
 * 实际运行的时候，我目前觉着，也是阻塞了 ？？？（但是阻塞的是协程？是这样么）
 *
 * 后续课程补充：
 * 1. runBlocking并非挂起函数；也就是说，调用它的线程会一直位于该函数之中，直到协程执行完毕为止。
 * 2. coroutineScope是挂起函数；也就是说，如果其中的协程挂起，那么coroutineScope函数也会挂起。这样，创建coroutineScope
 * 的外层函数就可以继续在同一个线程中执行了，该线程会【逃离】coroutineScope之外，并且可以做其他一些事情。
 */
fun main() = runBlocking {
    println(Thread.currentThread().name)

    launch {
        delay(1000)
        println("my job1 -> ${Thread.currentThread().name}")
    }

    println("person -> ${Thread.currentThread().name}")

    coroutineScope {
        launch {
            delay(10 * 1000)
            println("my job2 -> ${Thread.currentThread().name}")
        }

        delay(5 * 1000)
        println("hello world -> ${Thread.currentThread().name}")
    }

    launch {
        println("block? -> ${Thread.currentThread().name}")
    }

    println("welcome -> ${Thread.currentThread().name}")
}