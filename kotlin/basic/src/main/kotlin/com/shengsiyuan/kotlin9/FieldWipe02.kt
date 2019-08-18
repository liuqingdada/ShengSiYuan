package com.shengsiyuan.kotlin9

import com.shengsiyuan.kotlin.Mychild2
import javax.sound.midi.Soundbank

/**
 * Created by yangliuqing
 * 2019-03-12.
 * Email: 1239604859@qq.com
 */

class MyClass {

    val a
        @JvmName("getAValue")
        get() = 20

    fun getA() = 30
}

fun main() {
    val myClass = MyClass()
    println(myClass.getA())
    println(myClass.a)
}