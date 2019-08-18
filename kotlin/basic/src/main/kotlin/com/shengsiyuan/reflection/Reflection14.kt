package com.shengsiyuan.reflection

import kotlin.reflect.full.memberProperties

/**
 * Created by yangliuqing
 * 2019-05-19.
 * Email: 1239604859@qq.com
 */

class MyTest(private var a: String, val flag: Boolean, var age: Int)

fun main() {
    val kclass = MyTest::class
    println(kclass.memberProperties)
}