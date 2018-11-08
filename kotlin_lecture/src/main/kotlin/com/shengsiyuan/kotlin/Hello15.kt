package com.shengsiyuan.kotlin

import java.time.LocalDateTime

/**
 * Created by liuqing
 * 2018/11/8.
 * Email: suhen0420@163.com
 */

open class Fruit {
    open fun name() {
        println("fruit")
    }

    fun date() {
        println(LocalDateTime.now())
    }
}

class Apple : Fruit() {
    override fun name() {
        println("apple")
    }
}

open class Orange: Fruit() {
    final override fun name() {
        println("orange")
    }
}


fun main() {
    val apple = Apple()
    apple.name()
    apple.date()
}
