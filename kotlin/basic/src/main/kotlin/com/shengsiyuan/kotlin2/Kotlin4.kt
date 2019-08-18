package com.shengsiyuan.kotlin2

/**
 * Created by suhen
 * 18-12-5.
 * Email: 1239604859@qq.com
 */

class ParameterizedClass<A>(private val value: A) {

    fun getValue(): A {
        return this.value
    }
}

class ParameterizedProducer<out T>(private val value: T) {

    fun get(): T = this.value
}

class ParameteriedConsumer<in T>() {

    fun toString(value: T): String {
        return value.toString()
    }
}

fun main() {
    val parameterizedClass = ParameterizedClass("hello world")
    val value = parameterizedClass.getValue()

    println(value.javaClass)

    val parameterizedProducer = ParameterizedProducer("hello")
    val any: ParameterizedProducer<Any> = parameterizedProducer

    val parameteriedConsumer = ParameteriedConsumer<Number>()
    val ref : ParameteriedConsumer<Int> = parameteriedConsumer
}