package com.shengsiyuan.kotlin8

/**
 * Created by yangliuqing
 * 2019-03-10.
 * Email: 1239604859@qq.com
 */

fun main() {
    val javaException = JavaException()
//    javaException.openFile()

    var clazz = javaException::class.java // Kotlin 1.1
    println(clazz)

    clazz = javaException.javaClass // 一直都有
    println(clazz)

    println("=============")

    println(JavaException::class.java)
    println(JavaException::javaClass)
}