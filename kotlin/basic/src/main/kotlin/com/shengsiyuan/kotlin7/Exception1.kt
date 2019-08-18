package com.shengsiyuan.kotlin7

/**
 * Created by yangliuqing
 * 2019-03-03.
 * Email: 1239604859@qq.com
 *
 * 异常，Kotlin 中的 try 是个表达式
 * Kotlin 中没有 checked exception
 */

fun main() {
    println(parseInt("123"))
    println(parseInt("abc"))


}

fun parseInt(i: String): Int? {
    return try {
        Integer.parseInt(i)
    } catch (e: NumberFormatException) {
        null
    } finally {
        println("finally invoked")
    }
}