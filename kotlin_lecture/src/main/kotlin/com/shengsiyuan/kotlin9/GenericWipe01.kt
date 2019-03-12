package com.shengsiyuan.kotlin9

/**
 * Created by yangliuqing
 * 2019-03-12.
 * Email: 1239604859@qq.com
 */

fun List<String>.aList(): List<String> {
    return listOf("hello", "world")
}

@JvmName("aList2")
fun List<Int>.aList(): List<Int> {
    return listOf(1, 2)
}

fun main() {
    val list1 = mutableListOf<String>()
    println(list1.aList())

    val list2 = listOf<Int>()
    println(list2.aList())
}