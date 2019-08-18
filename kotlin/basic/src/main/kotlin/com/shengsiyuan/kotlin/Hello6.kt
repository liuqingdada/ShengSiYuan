package com.shengsiyuan.kotlin

/**
 * Created by liuqing
 * 2018/11/6.
 * Email: suhen0420@163.com
 */

fun main() {
    val array: IntArray = intArrayOf(1, 2, 3, 4, 5) // vararg 可变参数

    for (item: Int in array) {
        println(item)
    }

    for (i: Int in array.indices) {
        println("array[$i] = ${array[i]}")
    }

    for ((index: Int, value: Int) in array.withIndex()) {
        println("array index [$index] = $value")
    }
}