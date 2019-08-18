package com.shengsiyuan.kotlin5

/**
 * Created by yangliuqing
 * 2019-01-24.
 * Email: 1239604859@qq.com
 *
 * 中缀符号  infix notation
 *
 * 函数还可以通过中缀符号的形式来调用，需要满足如下三个条件
 * 1. 是成员函数或是扩展函数
 * 2. 拥有单个参数
 * 3. 声明时使用 infix 关键字
 */

class InfixTest(private val index: Int) {
    infix fun add(added: Int) = index + added
}

fun main() {
    val infixTest = InfixTest(0)

    // 以下两种方式等价
    println(infixTest.add(2))
    println(infixTest add 2)
}
