package com.shengsiyuan.kotlin2

/**
 * Created by suhen
 * 18-11-18.
 * Email: 1239604859@qq.com
 */

// 密封类 sealed class
// 表示一种受限制的层次解构, 父类和子类之间
// 密封类和它的子类必须在同一个文件当中
// 密封类是抽象的
// 密封类的构造方法是私有的

sealed class Calculator

class Add : Calculator()

class Substract : Calculator()

object Multiply : Calculator()

fun calculate(a: Int, b: Int, calculator: Calculator) = when (calculator) {
    is Add -> a + b
    is Substract -> a - b
    is Multiply -> a * b
}

fun main() {
    println(calculate(1, 2, Add()))
    println(calculate(1, 2, Substract()))
    println(calculate(2, 2, Multiply))
}