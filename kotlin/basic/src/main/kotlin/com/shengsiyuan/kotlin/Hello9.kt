package com.shengsiyuan.kotlin

/**
 * Created by liuqing
 * 2018/11/7.
 * Email: suhen0420@163.com
 */

fun main() {
    val array = listOf<String>("hello", "world", "hello world", "welcome", "goodbye")

    for (item in array) {
        println(item)
    }

    println("---------")

    when {
        "world" in array -> println("world in array")
    }

    println("---------")

    array.filter { it.length > 5 }
        .map { it.toUpperCase() }
        .sorted()
        .forEach { println(it) }
}