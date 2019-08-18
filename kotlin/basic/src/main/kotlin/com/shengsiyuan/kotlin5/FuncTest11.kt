package com.shengsiyuan.kotlin5

/**
 * Created by yangliuqing
 * 2019-02-04.
 * Email: 1239604859@qq.com
 *
 * 闭包
 */

fun main() {
    var sum = ""

    val strs = arrayOf("hello", "world", "bye")

    strs.filter {
        it.length > 3
    }.forEach {
        sum += "$it "
    }

    println(sum)
}
