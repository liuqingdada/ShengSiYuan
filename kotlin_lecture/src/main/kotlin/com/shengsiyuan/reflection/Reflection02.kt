package com.shengsiyuan.reflection

/**
 * Created by yangliuqing
 * 2019-03-13.
 * Email: 1239604859@qq.com
 */

open class Parent

class Son : Parent()

class Daughter : Parent()

fun main() {
    val son: Parent = Son()
    val daughter: Parent = Daughter()

    println(son::class)
    println(son::class.java)
    println(son.javaClass)

    println(daughter::class)
    println(daughter::class.java)
    println(daughter.javaClass)
}