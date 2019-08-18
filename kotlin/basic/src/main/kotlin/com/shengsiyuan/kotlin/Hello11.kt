package com.shengsiyuan.kotlin

/**
 * Created by liuqing
 * 2018/11/7.
 * Email: suhen0420@163.com
 *
 * in kotlin, 一个类可以有一个primary构造方法以及一个或多个secondary构造方法
 * primary构造方法是类头(class header)的一部分，它位于类名后面，可以拥有若干参数
 * 如果primary构造方法没有任何注解或者可见性关键字修饰，那么constructor关键字可以省略
 */

class EmptyClass

class MyClass constructor(name: String) {

    private val name = name.toUpperCase()

    init {
        println(name)
        println(this.name)
    }
}

fun main() {
    var myClass = MyClass("zhangsan")
}

