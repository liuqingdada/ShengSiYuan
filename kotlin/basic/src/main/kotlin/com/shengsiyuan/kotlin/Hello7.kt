package com.shengsiyuan.kotlin

import java.util.*

/**
 * Created by liuqing
 * 2018/11/6.
 * Email: suhen0420@163.com
 */

fun main() {
    val nextInt = Random().nextInt()
    println(nextInt)
    println(myPrint("hello") + "\n" +
            myPrint("world") + "\n" +
            myPrint("hello world") + "\n" +
            myPrint("..."))

    println(myPrint2("hello") + "\n" +
            myPrint2("world") + "\n" +
            myPrint2("hello world") + "\n" +
            myPrint2("..."))

    val x: Int = Random().nextInt(32)
    val ret = when (x) {
        1 -> {
            println("x = 1")
            100
        }
        2, 3, 4, 5 -> {
            println("x = 2, 3, 4, 5; ...$x")
            101
        }
        in 6..20 -> {
            println("x in 6 .. 20; ...$x")
            102
        }
        else -> {
            println("x is other value; ...$x")
            103
        }
    }
    println(ret)
}

fun myPrint(str: String): String {
    return when (str) {
        "hello" -> "HELLO"
        "world" -> "WORLD"
        "hello world" -> "HELLO WORLD"
        else -> "\tother input"
    }
}

fun myPrint2(str: String) = when (str) {
    "hello" -> "HELLO"
    "world" -> "WORLD"
    "hello world" -> "HELLO WORLD"
    else -> "other input"
}

