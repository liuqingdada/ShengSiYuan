package com.shengsiyuan.kotlin

import org.junit.Test

/**
 * Created by liuqing
 * 2018/11/9.
 * Email: suhen0420@163.com
 */

class Code {
    @Test
    fun test() {
        val runtime = Runtime.getRuntime()
        val maxMemory = runtime.maxMemory() / 1024 / 1024
        val totalMemory = runtime.totalMemory() / 1024 / 1024
        val freeMemory = runtime.freeMemory() / 1024 / 1024
        val used = totalMemory - freeMemory

        println(maxMemory)
        println(totalMemory)
        println(freeMemory)
        println(used)

    }
}