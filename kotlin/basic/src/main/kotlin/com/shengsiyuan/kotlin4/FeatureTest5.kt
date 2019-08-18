package com.shengsiyuan.kotlin4

import java.time.LocalDateTime

/**
 * Created by yangliuqing
 * 2019-01-06.
 * Email: 1239604859@qq.com
 *
 * map 委托
 *
 * 将属性存储到map中
 *
 * 一种常见的应用场景是将属性值存储到map当中
 * 这通常出现在JSON解析或是一些动态行为，一个地方存好全集，供给各个地方使用
 * 这种情况中，可以使用map实例作为委托，作为类中属性的委托
 */

class Student(map: Map<String, Any?>) {

    val name: String by map

    val address: String by map

    val age: Int by map

    val birthday: LocalDateTime by map
}

class Student2(map: MutableMap<String, Any?>) {

    var address: String by map
}

fun main() {
    val student = Student(mapOf(
            "name" to "zhangsan",
            "address" to "BeiJing",
            "age" to 20,
            "birthday" to LocalDateTime.now()
    ))

    println(student.name)
    println(student.address)
    println(student.age)
    println(student.birthday)

    println("------")

    val map = mutableMapOf<String, Any?>(
            "address" to "ShangHai"
    )
    val student2 = Student2(map)
    println(map["address"])
    println(student2.address)

    student2.address = "San Francisco"

    println(map["address"])
    println(student2.address)
}