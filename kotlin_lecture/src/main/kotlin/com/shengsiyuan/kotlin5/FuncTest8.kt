package com.shengsiyuan.kotlin5

/**
 * Created by yangliuqing
 * 2019-02-03.
 * Email: 1239604859@qq.com
 */

fun main() {
    val strings = arrayOf("hello", "world", "bye", "Hello World", "welcome")

    strings.filter { it.contains("h", ignoreCase = true) }.forEach { println(it) }

    println("---------")

    strings.filter { it.length > 4 }.forEach { println(it) }

    println("--------")

    strings.filter { it.endsWith("d", true) }
            .map { it.toUpperCase() }
            .forEach { println(it) }
}