package com.shengsiyuan.reflection

import kotlin.reflect.KClass

/**
 * Created by yangliuqing
 * 2019-05-19.
 * Email: 1239604859@qq.com
 */

fun main() {
    val str = "Kotlin"
    val kClass: KClass<out String> = str::class
    println(kClass)

    val kClassType: KClass<String> = String::class
    println(kClassType)

    val kclass1 = "kotlin"::class
    val kclass2 = "java"::class
    val kclass3 = "ruby"::class
    println(kclass1 == kclass2)
    println(kclass2 === kclass3)
}