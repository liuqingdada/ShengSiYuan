package com.shengsiyuan.kotlin

import java.util.*

/**
 * Created by suhen
 * 18-11-18.
 * Email: 1239604859@qq.com
 */

// 扩展的作用域

// 1. 扩展函数所定义的类实例叫做分发接收者 dispatch receiver
// 2. 扩展函数所扩展的那个类叫做扩展接收者 extension receiver
// 3. 当以上两个名字出现冲突时, 扩展接收者的优先级最高

class DD {
    fun method() {
        println("dd method")
    }
}

class EE {
    // DD 的扩展只能在 EE 中使用

    fun method2() {

    }

    fun DD.hello() { // DD: extension receiver, EE: dispatch receiver
        method()
        method2()
    }

    fun world(dd: DD) {
        dd.hello()
    }

    fun DD.output() {
        println(this.toString())
        println(this@EE.toString())
    }

    fun test() {
        val dd = DD()
        dd.output()
    }
}

fun main() {
    EE().test()
}

// 扩展可以很好的解决Java中充斥的各种辅助类问题

fun <E> List<E>.swap(i: Int, j: Int) {
    Collections.swap(this, i, j)
}