package com.shengsiyuan.reflection

import kotlin.reflect.jvm.javaField
import kotlin.reflect.jvm.javaGetter
import kotlin.reflect.jvm.javaSetter

/**
 * Created by yangliuqing
 * 2019-05-19.
 * Email: 1239604859@qq.com
 */

class T(var x: Int)

fun main() {
    println(T::x.javaField)
    println(T::x.javaGetter)
    println(T::x.javaSetter)
    println("----------")

    val t = T(100)
    println(t.javaClass)
    println(t.javaClass.kotlin)

    println(String.javaClass)
    println(String.javaClass.kotlin)
    //println(java.lang.String.javaClass)
    //println(java.lang.String.javaClass.kotlin)
    println(java.lang.String::class)
    println(java.lang.String::class.java)
    println(java.lang.String::class.java.kotlin)
}
