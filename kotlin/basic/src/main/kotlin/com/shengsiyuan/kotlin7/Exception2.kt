package com.shengsiyuan.kotlin7

/**
 * Created by yangliuqing
 * 2019-03-03.
 * Email: 1239604859@qq.com
 *
 * throw 在 Kotlin 中是个表达式，这样我们可以将 throw 作为 Elvis 表达式的一部分
 * throw 表达式的类型是一种特殊的类型：Nothing
 * 在自己的代码中，可以使用 Nothing 来标记永远不会返回的函数
 *
 * Nothing? 其实表示的就是 null
 */

fun main() {
    val str: String? = "s"
//    val str: String? = null
    val ret = str ?: throw IllegalArgumentException("value must be no null")
    println(ret)

    println("--------")

    val str2 = str ?: method("hello")
    println(str2)

    println("--------")

    var v = null
    println(v is Nothing?)

    var v2 = listOf(null)
    println(v2 is List<Nothing?>)
}


fun method(message: String): Nothing {
    throw IllegalArgumentException(message)
}