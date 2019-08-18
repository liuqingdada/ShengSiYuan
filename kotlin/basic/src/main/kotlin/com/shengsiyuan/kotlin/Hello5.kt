package com.shengsiyuan.kotlin

/**
 * Created by liuqing
 * 2018/11/6.
 * Email: suhen0420@163.com
 */

fun main() {
    println(convert2Uppercase("hello world"))
    println(23)
}

fun convert2Uppercase(any: Any): String? {
    return if (any is String) {
        any.toUpperCase()
    } else {
        null
    }
}
