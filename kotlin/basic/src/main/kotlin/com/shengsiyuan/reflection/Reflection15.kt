package com.shengsiyuan.reflection

import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.full.memberFunctions

/**
 * Created by yangliuqing
 * 2019-05-19.
 * Email: 1239604859@qq.com
 */

class MyTest2 {
    companion object {
        @JvmStatic
        fun staticFun() {
        }
    }

    fun print() {
        println("this is print")
    }

    fun print(s: String) {
        println(s)
    }
}

fun main() {
    val kclass = MyTest2::class
    println(kclass.memberFunctions.forEach {
        println(it)
    })
    println("==========")

    println(kclass.declaredFunctions.forEach {
        println(it)
    })
}