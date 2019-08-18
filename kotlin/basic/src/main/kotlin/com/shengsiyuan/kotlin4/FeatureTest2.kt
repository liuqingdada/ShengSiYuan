package com.shengsiyuan.kotlin4

import kotlin.reflect.KProperty

/**
 * Created by suhen
 * 18-12-11.
 * Email: 1239604859@qq.com
 *
 * 委托属性 delegated property
 *
 * 有四种情况实际中比较有用
 * 1. 延迟属性
 * 2. 可观测属性
 * 3. 非空属性
 * 4. map属性
 */

class MyDelegate {
    operator fun getValue(thisRef: Any, property: KProperty<*>): String = "$thisRef, your delegated property name is ${property.name}"

    operator fun setValue(thisRef: Any, property: KProperty<*>, value: Any) = println("$thisRef, new value is $value")
}

class MyPropertyClass {
    var str: String by MyDelegate()
}

fun main() {
    val myPropertyClass = MyPropertyClass()
    myPropertyClass.str = "hello world"
    println(myPropertyClass.str)
}