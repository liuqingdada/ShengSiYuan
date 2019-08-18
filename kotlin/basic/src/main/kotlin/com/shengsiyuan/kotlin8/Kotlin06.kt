package com.shengsiyuan.kotlin8

/**
 * Created by yangliuqing
 * 2019-03-10.
 * Email: 1239604859@qq.com
 */

class JKClass

fun test() {
    println("test")
}

/**
 * val -> Java字节码：private static final 的属性 + public static final 的 getter 方法
 *
 * var -> Java字节码：private static 的属性 + public static final 的 getter 和 setter 方法
 *
 * const val -> Java字节码：public static final 的属性
 */
var str: String = "hello"
