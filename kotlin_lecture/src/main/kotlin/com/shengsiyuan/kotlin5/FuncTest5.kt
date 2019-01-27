package com.shengsiyuan.kotlin5

/**
 * Created by yangliuqing
 * 2019-01-27.
 * Email: 1239604859@qq.com
 *
 * 高阶函数（high-order function）与Lambda
 *
 * Lambda表达式的格式要求：
 * 1. 一个Lambda表达式总是被一对花括号所包围
 * 2. 其参数（如果有的话）位于 -> 之前，参数类型是可以省略掉的
 * 3. 执行体位于 -> 之后
 *
 * 在Kotlin中，如果一个函数的最后一个参数是函数，那么可以将Lambda表达式作为实参传递进去
 * 并且可以在调用时方法圆括号外去使用
 */

val multiply: (Int, Int) -> Int = { a, b -> a + b }

val add: (Int, Int) -> Int = { a, b -> a + b }

val substract = { a: Int, b: Int -> a - b }

val myAction = { println("hello") }

val maybeReturnNull: (Int, Int) -> Int? = { _, _ -> null }

val maybeFuncNull: ((Int, Int) -> Int)? = null