package com.shengsiyuan.reflection

import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.functions
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.valueParameters

/**
 * Created by yangliuqing
 * 2019-05-19.
 * Email: 1239604859@qq.com
 */

class ReflectionClass2 {
    var name = "suhen"

    fun print(info: String) {
        println(info)
    }

    fun print() {
        println("print this")
    }
}

fun main() {
    val kclass = ReflectionClass2::class
    val test = ReflectionClass2()
    val func = kclass.functions.find { it.name == "print" && it.valueParameters.size == 1 }
    func?.call(test, "hello")

    val prop = kclass.memberProperties.find { it.name == "name" }
    println(prop?.get(test))
    println(prop?.call(test))

    if (prop is KMutableProperty<*>) {
        prop.setter.call(test, "Cooper")
        println(prop.get(test))
    }

    // Kotlin 48 补充
    // parameters 的 size 包含具体实例所占用的数量
    val func2 = kclass.functions.find { it.name == "print" && it.parameters.size == 2 }
    func2?.call(test, "world")
}