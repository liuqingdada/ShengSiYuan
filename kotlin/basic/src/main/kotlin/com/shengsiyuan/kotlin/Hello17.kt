package com.shengsiyuan.kotlin

/**
 * Created by liuqing
 * 2018/11/8.
 * Email: suhen0420@163.com
 */

interface A {

    fun method()

    fun method2() {
        println("A")
    }
}

open class B {
    open fun method2() {
        println("B")
    }
}

class C : A, B() {
    override fun method() {
    }

    override fun method2() {
        super<A>.method2()
        super<B>.method2()
        println("C")
    }

}

fun main() {
    val c = C()
    c.method2()
}
