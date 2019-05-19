package com.shengsiyuan.reflection

/**
 * Created by yangliuqing
 * 2019-05-19.
 * Email: 1239604859@qq.com
 *
 * 构造方法引用 Constructor Reference
 * 注意：
 * 1、函数对象的参数要与构造方法的参数保持一致（体现在参数个数与参数类型上）
 * 2、函数对象的返回结果要与构造方法所在类的类型保持一致
 */

class B(val x: Int)

fun myMethod(x: Int, factory: (x: Int) -> B) {
    val b = factory(x)
    println(b.x)
}

fun main() {
    myMethod(100, ::B)
}