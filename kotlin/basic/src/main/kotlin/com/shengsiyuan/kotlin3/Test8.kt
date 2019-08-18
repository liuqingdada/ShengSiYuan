package com.shengsiyuan.kotlin3

/**
 * Created by suhen
 * 18-12-9.
 * Email: 1239604859@qq.com
 *
 * 类似于Java的匿名内部类, Kotlin 对象表达式的代码可以访问到外层的变量, 外层无需声明成final
 */

fun main() {
    var i = 100

    val o = object {
        fun method() {
            i++
        }
    }

    o.method()

    println(i)
}
