package com.shengsiyuan.kotlin5

/**
 * Created by yangliuqing
 * 2019-01-24.
 * Email: 1239604859@qq.com
 *
 * 内联函数 inline function
 */

inline fun calculate(a: Int, b: Int) = a + b

fun main() {
    println(calculate(1, 2))
}
