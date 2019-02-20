package com.shengsiyuan.kotlin6

/**
 * Created by yangliuqing
 * 2019-02-20.
 * Email: 1239604859@qq.com
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
}