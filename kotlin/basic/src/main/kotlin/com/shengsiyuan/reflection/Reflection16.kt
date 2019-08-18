package com.shengsiyuan.reflection

/**
 * Created by yangliuqing
 * 2019-05-19.
 * Email: 1239604859@qq.com
 */

class ReflectionClass(value: Int) {

    constructor(value: Int, age: Int) : this(value) {
        println("seconfary constructor 1")
    }

    constructor(value: Int, flag: Boolean) : this(value) {
        println("secondary constructor 2")
    }

    fun print() {
        println("print this")
    }
}

fun main() {
    val kclass = ReflectionClass::class
    println(kclass.constructors.forEach { println(it) })
}