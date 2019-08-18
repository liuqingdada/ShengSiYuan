package com.shengsiyuan.kotlin9

import java.io.FileNotFoundException
import java.io.IOException

/**
 * Created by yangliuqing
 * 2019-03-12.
 * Email: 1239604859@qq.com
 *
 * 在 Kotlin 中，不存在 checked exception
 */

class Email {
    @Throws(FileNotFoundException::class, IOException::class, ClassNotFoundException::class)
    fun method(x: Int) {
        println("method invoked")

        when (x) {
            1 -> throw FileNotFoundException()
            2 -> throw IOException()
            else -> throw ClassNotFoundException()
        }
    }
}