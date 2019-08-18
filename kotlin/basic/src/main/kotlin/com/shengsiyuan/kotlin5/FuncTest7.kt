package com.shengsiyuan.kotlin5

/**
 * Created by yangliuqing
 * 2019-02-03.
 * Email: 1239604859@qq.com
 */

fun String.filter(predicate: (Char) -> Boolean): String {
    val sb = StringBuilder()
    this.forEach {
        if (predicate(it)) {
            sb.append(it)
        }
    }
    return sb.toString()
}

fun main() {
    val str = "abc2dfe9xy"
    println(str.filter { it.isLetter() })
    println(str.filter { it.isDigit() })
}