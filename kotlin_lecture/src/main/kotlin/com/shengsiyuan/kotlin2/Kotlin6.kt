package com.shengsiyuan.kotlin2

/**
 * Created by suhen
 * 18-12-6.
 * Email: 1239604859@qq.com
 */

// use-site variance (type projection), 类型投影

/**
 * <? extends Any>
 */
fun copy(from: Array<out Any>, to: Array<Any>) {
    assert(from.size == to.size)

    for (i in from.indices) {
        to[i] = from[i]
    }
}

/**
 * <? super String>
 */
fun setValue(to: Array<in String>, index: Int, value: String) {
    assert(to.size > index)
    to[index] = value
}

fun main() {
    val from: Array<Int> = arrayOf(1, 2, 3, 4, 5)
    val to: Array<Any> = Array<Any>(5) { it -> "hello$it" }

    to.iterator().forEach { println(it) }

    copy(from, to)

    to.iterator().forEach { println(it) }

    val arr: Array<String> = Array(4) { "hello" }

    setValue(arr, 1, "world")

    arr.iterator().forEach { println(it) }

    println("===================")

    val arr2: Array<Any> = Array<Any>(4) { it -> "hello$it" }

    setValue(arr2, 1, "world")

    arr2.iterator().forEach { println(it) }

}