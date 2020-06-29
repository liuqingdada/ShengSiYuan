package com.cooper.coroutine

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

/**
 * Created by cooper
 * 2020/6/29.
 * Email: 1239604859@qq.com
 */
class LaunchTest {
    @Test
    fun launchTest1() = runBlocking<Unit> {
        launch {
            delay(1000)
            println("launch delay task")
        }
        launch {
            println("launch 0")
        }
        GlobalScope.launch {
//            delay(100)
            println("global launch 1")
        }
        launch {
//            delay(100)
            println("launch 1")
        }
        launch {
            println("launch 2")
        }
        GlobalScope.launch {
//            delay(100)
            println("global launch 2")
        }
        launch {
            println("launch 3")
        }
        repeat(100) {
            GlobalScope.launch {
                println("repeat Global launch: $it")
            }
        }
    }
}
