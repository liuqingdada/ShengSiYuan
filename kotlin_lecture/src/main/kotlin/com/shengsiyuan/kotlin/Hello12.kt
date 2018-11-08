package com.shengsiyuan.kotlin

/**
 * Created by liuqing
 * 2018/11/7.
 * Email: suhen0420@163.com
 */

class Person constructor(username: String) { // username 是个局部变量, 参数 -- value param
    private var username: String // username 是属性
    private var age: Int
    private var address: String

    init {
        this.username = username
        this.age = 20
        this.address = "BeiJing"
    }

    constructor(username: String, age: Int) : this(username) {
        this.username = username
        this.age = age
        this.address = "ShangHai"
    }

    constructor(username: String, age: Int, address: String) : this(username, age) {
        this.address = address
    }

    fun printInfo() {
        println("Person(username='$username', age=$age, address='$address')")
    }
}

fun main() {
    val person = Person("zhangsan")
    val person2 = Person("lisi", 22)
    val person3 = Person("wangwu", 24, "HangZhou")

    person.printInfo()
    person2.printInfo()
    person3.printInfo()
}
