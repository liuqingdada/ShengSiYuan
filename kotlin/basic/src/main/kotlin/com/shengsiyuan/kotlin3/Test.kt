package com.shengsiyuan.kotlin3

/**
 * Created by suhen
 * 18-12-8.
 * Email: 1239604859@qq.com
 */

// 嵌套类 Nested Class

class OuterClass {

    private val str: String = "hello world"

    class NestedClass {

        fun nestedMethod() = "welcome"
    }
}

fun main() {
    println(OuterClass.NestedClass().nestedMethod())
}
