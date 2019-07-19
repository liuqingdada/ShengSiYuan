package com.shengsiyuan.reflection

import kotlin.reflect.full.companionObject
import kotlin.reflect.full.functions

/**
 * Created by yangliuqing
 * 2019-06-23.
 * Email: 1239604859@qq.com
 */

class Test18 {
    companion object {
        fun method() {
            println("cooper suhen")
        }
    }
}

fun main() {
    val kClass = Test18::class
    val companionObj = kClass.companionObject

    println(companionObj)
    companionObj?.functions?.find { it.name == "method" }?.call(Test18.Companion)

    val jClass = Test18.javaClass
    println(jClass)
    jClass.kotlin.functions.find { it.name == "method" }?.call((Test18.Companion))
}