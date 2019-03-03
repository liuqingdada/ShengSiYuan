package com.shengsiyuan.kotlin6

/**
 * Created by yangliuqing
 * 2019-03-03.
 * Email: 1239604859@qq.com
 */

fun main() {
    val numbers = listOf<Int>(1, 2, 3, 4)

    println(numbers.first())
    println(numbers.last())

    numbers.filter { it % 2 == 0 }.forEach { println(it) }

    println("--------")

    val mayNulls  = mutableListOf(1, 2, 3) // , null)
    val noNulls = mayNulls.requireNoNulls()
    println(noNulls)

    println("------")

    if(noNulls.none { it > 10 }){
        println("no elements > 10")
    }

    val firstOrNull = mayNulls.firstOrNull()
    val lastOrNull = mayNulls.lastOrNull()

    println("--------")

    val myMap = hashMapOf("hello" to 1, "world" to 2)
    println(myMap["hello"])
    println(myMap[""])

    println("----------")

    val myMap2: Map<String, Int> = HashMap(myMap)
    println(myMap2)

}