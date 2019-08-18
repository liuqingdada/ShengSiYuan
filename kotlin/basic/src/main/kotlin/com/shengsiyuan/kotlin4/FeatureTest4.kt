package com.shengsiyuan.kotlin4

import kotlin.properties.Delegates

/**
 * Created by suhen
 * 18-12-11.
 * Email: 1239604859@qq.com
 *
 * 非空属性
 * notNull适用于那些无法在初始化阶段就确定属性值的场合
 */

class MyPerson {
    var address: String by Delegates.notNull()
}

/*
    可观测属性 Observable
    veto 否决，禁止
 */

class Person {
    var age: Int by Delegates.observable(20) { property, oldValue, newValue ->
        println("${property.name}, old = $oldValue, new = $newValue")
    }
}

class Person2 {
    var age: Int by Delegates.vetoable(20) { property, oldValue, newValue ->
        when {
            oldValue < newValue -> true
            else -> false
        }
    }
}

fun main() {
    val myPerson = MyPerson()
    myPerson.address = "suhen"
    println(myPerson.address)

    val person = Person()
    person.age = 30
    person.age = 40

    println("------")

    val person2 = Person2()
    person2.age = 30
    person2.age = 40
    person2.age = 10
    println(person2.age)

}
