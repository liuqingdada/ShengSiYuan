package com.shengsiyuan.kotlin8

/**
 * Created by yangliuqing
 * 2019-03-10.
 * Email: 1239604859@qq.com
 */

fun main() {
    val varArgs = VarArgs()
    val strings = arrayOf("hello", "world", "hello world")

    varArgs.strings(*strings) // spread operation *
}