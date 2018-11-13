package com.shengsiyuan.kotlin

/**
 * Created by suhen
 * 18-11-18.
 * Email: 1239604859@qq.com
 */

class ExtentionProperty

val ExtentionProperty.name: String
    get() = "hello"

fun main() {
    val ep = ExtentionProperty()

    println(ep.name)
}