package com.shengsiyuan.reflection

import kotlin.reflect.KClass

/**
 * Created by yangliuqing
 * 2019-03-13.
 * Email: 1239604859@qq.com
 */

fun main() {
    val kclass: KClass<String> = String::class
    println(kclass)

    println("==========")

    val clazz: Class<String> = String::class.java
    println(clazz)
}