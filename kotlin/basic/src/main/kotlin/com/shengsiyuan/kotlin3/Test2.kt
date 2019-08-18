package com.shengsiyuan.kotlin3

/**
 * Created by suhen
 * 18-12-8.
 * Email: 1239604859@qq.com
 */

// 内部类 Inner Class

/*
    1.嵌套类对应于Java中的静态内部类

    2.内部类对应于Java的非静态内部类
 */

class OuterClass2 {

    private val str: String = "hello"

    inner class InnerClass {

        fun innerMethod() = this@OuterClass2.str
    }
}

fun main() {
    println(OuterClass2().InnerClass().innerMethod())
}
