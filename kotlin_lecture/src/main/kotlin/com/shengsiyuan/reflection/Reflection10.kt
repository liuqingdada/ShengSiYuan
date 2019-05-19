package com.shengsiyuan.reflection

/**
 * Created by yangliuqing
 * 2019-05-19.
 * Email: 1239604859@qq.com
 *
 * 引用特定对象的一个实例方法, 也可以引用特定对象的属性
 */

fun main() {
    val str = "abc"
    val getRefrence = str::get
    println(getRefrence(1))

    val refLength = str::length
    println(refLength)
    println(refLength.get())

    val refLen = String::length
    println(refLen)
    println(refLen.get(str))
}