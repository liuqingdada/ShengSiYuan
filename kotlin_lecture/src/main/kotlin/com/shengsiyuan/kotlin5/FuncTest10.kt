package com.shengsiyuan.kotlin5

/**
 * Created by yangliuqing
 * 2019-02-04.
 * Email: 1239604859@qq.com
 *
 * 匿名函数
 */

fun main() {
    fun(x: Int, y: Int) = x + y

    fun(x: Int, y: Int): Int {
        return x + y
    }

    val strings = arrayOf("hello", "world", "bye")

    strings.filter(fun(item: String): Boolean = item.length > 3).forEach(fun(item: String) = println(item))
}
