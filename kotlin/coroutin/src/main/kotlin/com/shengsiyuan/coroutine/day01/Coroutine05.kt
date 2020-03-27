package com.shengsiyuan.coroutine.day01

import kotlinx.coroutines.*

/**
 * Created by liuqing.yang
 * 2020/3/17.
 * Email: 1239604859@qq.com
 *
 * 对该示例来说，当我们在协程的finally块中使用了挂起函数时，会导致出现CancellationException异常，
 * 原因在于运行着的代码块的协程已经被取消了。通常情况下，这并不会产生什么问题，因为大多数关闭操作（比如说
 * 取消一个job，关闭网络连接等）通常都是非阻塞的，并不需要挂起函数；然而，在极少数情况下，当我们在一个取消的协程中
 * 进行挂起操作时，我们可以将相应的代码放置到withContext(NonCancellable){}当中，在这种结构中，我们实际上使用了
 * withContext函数与NonCancellable上下文
 */

fun main() {
//    test()
    testNonCancellable()
}

/**
 * 取消协程后依旧调用挂起函数会抛出异常
 */
fun test() = runBlocking {
    val job = launch {
        try {
            repeat(100) {
                println("job repeat $it")
                delay(500)
            }
        } finally {
            println("execute finally")
            delay(1000)
            println("after delay 1000")
        }
    }

    delay(1300)
    println("hello")

    job.cancelAndJoin()
    println("world")
}

fun testNonCancellable() = runBlocking {
    val job = launch {
        try {
            repeat(100) {
                println("job repeat $it")
                delay(500)
            }
        } finally {
            withContext(NonCancellable) {
                println("execute finally")
                delay(1000)
                println("after delay 1000")
            }
        }
    }

    delay(1300)
    println("hello")

    job.cancelAndJoin()
    println("world")
}