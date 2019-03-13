package com.shengsiyuan.reflection

/**
 * Created by yangliuqing
 * 2019-03-13.
 * Email: 1239604859@qq.com
 *
 * 函数（方法）引用
 *
 * 支持重载
 *
 * ::multyply 表示函数类型 (Int) -> Int
 *                       (String) -> Int
 */

fun multiply(x: Int) = 3 * x

fun multiply(s: String) = 10

val multiplyInt: (Int) -> Int = ::multiply
val multiplyStr: (String) -> Int = ::multiply

val stringGet: (String, Int) -> Char = String::get // (Class, Params...) -> + 返回值; 参数类型和返回类型可以定位到确定的函数
val stringGet2: String.(Int) -> Char = String::get // Class. + 函数（方法）引用; 某个类的某个函数

fun getCharFromString(func: String.(Int) -> Char, string: String, index: Int) {
    println(func(string, index))
}

fun main() {
    val values = listOf<Int>(1, 2, 3, 4)
    values.map(::multiply).forEach { println(it) }
    values.map { multiply(it) }.forEach { println(it) }

    println("========")

    val strings = listOf<String>("a", "b", "c", "d")
    println(strings.map(::multiply))

    println("=========")

    getCharFromString(stringGet, "hello", 1)
    getCharFromString(stringGet2, "world", 1)
}