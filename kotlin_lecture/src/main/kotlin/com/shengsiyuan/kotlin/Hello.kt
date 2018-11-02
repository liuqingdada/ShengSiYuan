package com.shengsiyuan.kotlin

import java.util.function.BinaryOperator
import java.util.function.Consumer

/**
 * Created by liuqing
 * 2018/11/2.
 * Email: suhen0420@163.com
 */

fun main() { // args: Array<String>
    println("hello kotlin")

    val list: List<String> = listOf("hello", "world", "kotlin", "ppap")

    for (str in list) {
        println(str)
    }

    println("=========")

    list.forEach { println(it) }

    println("=========")

    list.forEach(Consumer { println(it) })

    println("===========")

    list.forEach(System.out::println)

    println(sum(1, 2))

    println(sum(1, 2, BinaryOperator { t, u -> t + u }))

    f2(3, 4)
}

fun sum(a: Int, b: Int): Int {
    return a + b
}

fun sum2(a: Int, b: Int) = a + b

fun <T> sum(t: T, u: T, op: BinaryOperator<T>) = op.apply(t, u)

//fun f(): Void {
//
//}

fun f2(a: Int, b: Int): Unit {
    println("$a + $b = ${a + b}")
}
