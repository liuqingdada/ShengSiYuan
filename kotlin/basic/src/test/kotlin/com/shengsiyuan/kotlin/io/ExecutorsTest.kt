package com.shengsiyuan.kotlin.io

import com.shengsiyuan.nio.serialExecute
import org.junit.Test

/**
 * Created by cooper
 * 2020/6/1.
 * Email: 1239604859@qq.com
 */
class ExecutorsTest {
    @Test
    fun presure() {
        for (i in 0..Int.MAX_VALUE) {
            serialExecute {
                println("serial: $i")
            }
            println(i)
        }
        println("finish")
        Thread.sleep(Long.MAX_VALUE)
    }
}