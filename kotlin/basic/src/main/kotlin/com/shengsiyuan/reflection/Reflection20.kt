package com.shengsiyuan.reflection

import kotlin.reflect.KMutableProperty1
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.declaredMemberProperties

/**
 * Created by yangliuqing
 * 2019-08-08.
 * Email: 1239604859@qq.com
 */

class Reflection20 {
    var name = "Flutter"
    val price = 34.5
}

fun main() {
    val kClass = Reflection20::class
    val instance = kClass.createInstance()
    val props = kClass.declaredMemberProperties

    props.forEach {
        when (it.name) {
            "name" -> {
                val kmp = it as? KMutableProperty1<Reflection20, String>
                kmp?.set(instance, "Hadoop")
                println(kmp?.get(instance))
            }
            "price" -> {
                println(it.get(instance))
            }
        }
    }
}