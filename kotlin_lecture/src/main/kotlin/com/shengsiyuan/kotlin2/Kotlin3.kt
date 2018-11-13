package com.shengsiyuan.kotlin2

/**
 * Created by suhen
 * 18-11-18.
 * Email: 1239604859@qq.com
 */

// generics 泛型 变量类型的参数化

class MyGeneric<T>(val t:T)

fun main() {
    val mg = MyGeneric("hello world")
    println(mg.t)
}

// 协变 covariant
// 逆变 controvariant


