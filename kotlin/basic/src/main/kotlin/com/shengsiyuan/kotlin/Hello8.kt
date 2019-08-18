package com.shengsiyuan.kotlin

/**
 * Created by liuqing
 * 2018/11/7.
 * Email: suhen0420@163.com
 */

fun main() {
    val a = 5
    val b = 10

    if (a in 2..b) {
        println("a in the range")
    }

    if (a !in 2..b) {
        println("out of range")
    }

    for (i in 2..10) { // contain 10
        println(i)
    }

    println("---------")

    for (i in 2.rangeTo(10)) {
        println(i)
    }

    println("---------")

    for (i in 2..10 step 2) {
        println(i)
    }

    println("---------")

    for (i in 10 downTo 2 step 4) {
        println(i)
    }

}