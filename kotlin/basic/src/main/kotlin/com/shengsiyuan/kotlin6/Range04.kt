package com.shengsiyuan.kotlin6

/**
 * Created by yangliuqing
 * 2019-03-03.
 * Email: 1239604859@qq.com
 *
 * Range
 */

fun main() {
    var i = 4

    if (i in 1..5) {
        println("$i in range of 1 to 5")
    }

    for (i in 1..4) { // 闭区间
        println(i)
    }
    println("------------")

    for (i in 4..1) {
        println(i)
    }
    println("------------")

    for (i in 4 downTo 1) {
        println(i)
    }
    println("------------")

    for (i in 1..6 step 2) {
        println(i)
    }
    println("------------")

    for (i in 6 downTo 1 step 2) {
        println(i)
    }
    println("------------")

    for (i in 0 until 10) {
        println(i)
    }
    println("------------")

}