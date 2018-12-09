package com.shengsiyuan.kotlin3

/**
 * Created by suhen
 * 18-12-9.
 * Email: 1239604859@qq.com
 */

// 对象表达式 object expression
/*
    object [: 若干个父类型, 中间用逗号隔开] {}
 */

interface MyInterface {
    fun myPrint(i: Int)
}

abstract class MyAbsClass {
    abstract val age: Int

    abstract fun printClassInfo()
}

fun main() {
    val obj = object : MyInterface {
        override fun myPrint(i: Int) {
            println("i value is $i")
        }
    }
    obj.myPrint(100)

    val obj2 = object {
        init {
            println("obj2 init")
        }

        var p = "hello"

        fun method() = "method"
    }

    println("${obj2.method()}, ${obj2.p}")

    val obj3 = object : MyInterface, MyAbsClass() {
        override fun myPrint(i: Int) {
            println(i)
        }

        override val age: Int
            get() = 30

        override fun printClassInfo() {
            println("printClassInfo")
        }
    }

    obj3.myPrint(109)
    obj3.printClassInfo()
    println(obj3.age)
}
