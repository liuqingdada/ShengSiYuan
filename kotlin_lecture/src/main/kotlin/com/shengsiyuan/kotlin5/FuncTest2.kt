package com.shengsiyuan.kotlin5

/**
 * Created by yangliuqing
 * 2019-01-21.
 * Email: 1239604859@qq.com
 *
 * 一个方法中，只允许一个参数作为vararg，通常作为最后一个参数。
 * 如果vararg不是作为最后一个参数，那么其后的参数就需要通过具名参数形式进行传递
 * 如果其后的参数是函数类型，那么还可以通过在圆括号外传递Lambda表达式来实现
 */

// 完整形式
fun myPrint(string: String): Unit {
    println(string)
    return Unit
}

// 单表达式
fun add(a: Int, b: Int) = a + b

fun main() {

}