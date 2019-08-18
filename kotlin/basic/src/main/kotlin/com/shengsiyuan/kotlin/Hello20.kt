package com.shengsiyuan.kotlin

/**
 * Created by liuqing
 * 2018/11/8.
 * Email: suhen0420@163.com
 */

class ThePerson(address: String, name: String) {

    val age
        get() = 20

    var address = address
        get() {
            println("get invocked")
            return field
        }
        set(value) {
            println("set invocked")
            field = value
        }

    var name = name
    // lombok 框架

    // backing field, 支撑字段 (域)
    // backing property, 支撑属性, 另一个属性作为返回
}

fun main() {
    val person = ThePerson("shanghai", "zhangsan")
    println(person.age)
    println(person.address)
    person.address = "beijing"
    println(person.address)

    println(person.name)
    person.name = "lisi"
    println(person.name)
}