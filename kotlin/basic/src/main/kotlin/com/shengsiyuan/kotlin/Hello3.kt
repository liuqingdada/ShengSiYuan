package com.shengsiyuan.kotlin

/**
 * Created by liuqing
 * 2018/11/2.
 * Email: suhen0420@163.com
 */

@Suppress("ConstantConditionIf")
fun main() {
    val x = 10
    val y = 20

    var max: Int
    var min: Int

    max = kotlin.math.max(x, y)
    max = maxOf(x, y)

    max = if (x > y) x else y
    min = if (x > y) y else x

    max = if (x > y) {
        println("x > y")
        x
    } else {
        println("x <= y")
        y
    }

    min = if (x > y) {
        y
    } else {
        x
    }
}