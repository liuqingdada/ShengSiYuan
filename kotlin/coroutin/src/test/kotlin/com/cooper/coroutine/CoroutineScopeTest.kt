package com.cooper.coroutine

import kotlinx.coroutines.*
import kotlin.test.Test

/**
 * Created by cooper
 * 2020/5/28.
 * Email: 1239604859@qq.com
 */

class CoroutineScopeTest {

    @Test
    fun test() = runBlocking {
        println("test start")
        printThread()
        val result = coroutineScope {
            delay(5000)
            printThread()
            println("http coroutine scope finished")
            "Http Resp"
        }
        println("on get http result: $result")

        val fileData = coroutineScope {
            delay(1000)
            printThread()
            println("io coroutine scope finished")
            "File text"
        }
        println("on io operation finished: $fileData")

        println("test finished")
        delay(2000)
    }

    @Test
    fun test2() = runBlocking {
        println("test start")
        printThread()
        coroutineScope {
            printThread()
            coroutineScope {
                delay(4000)
                printThread()
                println("scope 1 finish")
            }

            coroutineScope {
                delay(1000)
                printThread()
                println("scope 2 finish")
            }
        }
        println("test end")
    }

    @Test
    fun test3() = runBlocking<Unit> {
        println("test start")
        printThread()

        launch(Dispatchers.Default) {
            println("launch a new corotine")
            printThread()

            coroutineScope {
                delay(4000)
                printThread()
                println("scope 1 finish")
            }

            coroutineScope {
                delay(1000)
                printThread()
                println("scope 2 finish")
            }
        }
    }

    @Test
    fun test4() = runBlocking {
        println("test start")
        coroutineScope<Unit> {
            launch {
                delay(200)
                println("launch 1")
            }
            launch {
                delay(100)
                println("launch 2")
            }
            // List<Deferred<String>>
            val jobs = (0 until 10).map {
                async {
                    delay(100)
                    "http://www.baidu.com"
                }
            }
            val list = jobs.awaitAll()
            println(list)
        }
        println("test end")
    }

    private fun printThread() {
        println(Thread.currentThread().name)
    }
}
