package com.shengsiyuan.kotlin5

/**
 * Created by yangliuqing
 * 2019-02-03.
 * Email: 1239604859@qq.com
 *
 * 默认情况下，lambda表达式中最后一个表达式的值会隐试作为该lambda表达式的返回值
 * 我们可以通过全限定的return语法来显示从lambda表达式返回值
 */

fun main() {
    val strings = arrayOf("hello", "world", "bye")

    strings.filter {
        val item = it.length > 3
        item
    }
}
