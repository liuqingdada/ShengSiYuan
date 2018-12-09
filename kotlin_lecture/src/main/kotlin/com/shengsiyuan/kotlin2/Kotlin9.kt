package com.shengsiyuan.kotlin2

/**
 * Created by suhen
 * 18-12-8.
 * Email: 1239604859@qq.com
 */

fun <T> getValue(t: T): T {
    return t
}

class UpperBoundsClass<T, S : List<T>>

class UpperBoundsClass2<T> where T : Comparable<T>, T : Any

fun main() {
    val item = getValue<Int>(3)
    println(item)

    val item2 = getValue("hello")
    println(item2)

    val upperBoundsClass = UpperBoundsClass<String, List<String>>()

    val upper = UpperBoundsClass2<String>()
    val upper2 = UpperBoundsClass2<String>()
}

