package com.shengsiyuan.reflection

/**
 * Created by yangliuqing
 * 2019-05-18.
 * Email: 1239604859@qq.com
 *
 * 属性引用 Property Reference
 *
 * 与函数引用类似 使用 ::
 *
 *
 */

const val a = 10

var b = 5

fun main() {
    println(::a)       // 表示KProperty<Int> 的属性对象
    println(::a.get()) // get 方法获取其值
    println(::a.name)  // name 属性获取其名字
    println("================")

    ::b.set(20)
    println(b)
    println(::b)
    println(::b.get())
    println(::b.name)
}
