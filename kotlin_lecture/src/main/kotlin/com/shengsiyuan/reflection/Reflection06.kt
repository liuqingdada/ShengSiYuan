package com.shengsiyuan.reflection

/**
 * Created by yangliuqing
 * 2019-05-18.
 * Email: 1239604859@qq.com
 */

class MyClass(val x: Int)

fun main() {
    val strings = listOf("a", "ab", "abc")
    println(strings.map(String::length))
    println("===========")

    val x = MyClass::x
    println(x.get(MyClass(10)))
}