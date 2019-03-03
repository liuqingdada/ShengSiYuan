package com.shengsiyuan.kotlin6

/**
 * Created by yangliuqing
 * 2019-02-20.
 * Email: 1239604859@qq.com
 *
 * 解构声明
 * Kotlin 允许我们为解构声明整体指定类型，也可以为每一个具体的 Component 指定类型
 */

data class MyResult(val result: String, val status: Int)

fun myMethod(): MyResult {
    return MyResult("success", 1)
}

fun myMethod2(): Pair<String, Int> {
    return Pair("failed", 0)
}

fun main() {
    val (result, status) = myMethod()
    println(result)
    println(status)

    val (result2, status2) = myMethod2()
    println(result2)
    println(status2)

    val map = mapOf<String, String>("1" to "a", "2" to "b", "3" to "c")
    for ((key, value) in map) {
        println("key: $key, value: $value")
    }

    println("=========")

    map.mapValues { entry -> "hello ${entry.value}" }.forEach { println(it) }

    println("-------------")

    map.mapValues { (_, value) -> "world, $value" }.forEach { println(it) }

    println("--------")

    map.mapValues { (_, value): Map.Entry<String, String> -> "$value person" }.forEach { println(it) }

    println("----------")

    map.mapValues { (_, value: String) -> "$value people" }.forEach { println(it) }
}