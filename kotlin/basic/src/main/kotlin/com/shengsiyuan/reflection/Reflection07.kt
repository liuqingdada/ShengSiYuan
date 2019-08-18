package com.shengsiyuan.reflection

/**
 * Created by yangliuqing
 * 2019-05-18.
 * Email: 1239604859@qq.com
 */

val String.firstChar: Char
    get() = this[0]

fun main() {
    val str = "xyz"
    println(str::firstChar.get())

    println(String::firstChar.get(str))
}