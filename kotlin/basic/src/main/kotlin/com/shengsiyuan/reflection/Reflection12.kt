package com.shengsiyuan.reflection

/**
 * Created by yangliuqing
 * 2019-05-19.
 * Email: 1239604859@qq.com
 */

class MyTestClass<K, V> {
    var k: K? = null
    var v: V? = null
}

fun main() {
    val testClass = MyTestClass::class
    println(testClass.typeParameters)

    val testClass2 = MyTestClass<String, Int>()
    println(testClass2::class.typeParameters)
}