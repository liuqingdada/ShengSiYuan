package com.shengsiyuan.kotlin

/**
 * Created by liuqing
 * 2018/11/13.
 * Email: suhen0420@163.com
 */

// 扩展
// 扩展函数的解析是静态的

class ExtentionTest {
    fun add(a: Int, b: Int) = a + b

    fun substract(a: Int, b: Int) = a - b

}

fun ExtentionTest.multiply(a: Int, b: Int) = a * b // 不会插入到原有的类上

fun main() {
    val extentionTest = ExtentionTest()

    println(extentionTest.add(1, 2))
    println(extentionTest.substract(1, 2))

    println(extentionTest.multiply(2, 3))
}

