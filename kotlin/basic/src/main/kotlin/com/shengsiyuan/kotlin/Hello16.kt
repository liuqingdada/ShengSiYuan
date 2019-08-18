package com.shengsiyuan.kotlin

/**
 * Created by liuqing
 * 2018/11/8.
 * Email: suhen0420@163.com
 */

open class MyParent {
    open val name: String = "parant"
}

open class MyChild : MyParent() {
    final override var name: String = "child"
}

class Mychild2(override val name: String) : MyParent()

///////////

open class MyParent3 {
    open fun method() {
        println("print method")
    }

    open val name: String get() = "parent"
}

class MyChild3 : MyParent3() {
    override fun method() {
        super.method()
        println("print child")
    }

    override val name: String
        get() = super.name + " and child"
}

fun main() {
    val myChild = MyChild()
    println(myChild.name)

    val myChild2 = Mychild2("zhangsan")
    println(myChild2.name)

    println("----------")

    val myChild3 = MyChild3()
    myChild3.method()
    println(myChild3.name)
}

