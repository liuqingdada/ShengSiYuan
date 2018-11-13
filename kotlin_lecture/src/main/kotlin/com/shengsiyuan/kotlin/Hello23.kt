package com.shengsiyuan.kotlin

/**
 * Created by liuqing
 * 2018/11/13.
 * Email: suhen0420@163.com
 */

// 扩展
// 扩展函数不会真正修改目标类, 也就是它并不会在目标类中插入新的属性和方法
// 扩展函数的解析是静态的, 也就是说不支持多态, 调用只取决于对象的声明类型

class ExtentionTest {
    fun add(a: Int, b: Int) = a + b

    fun substract(a: Int, b: Int) = a - b

}

fun ExtentionTest.multiply(a: Int, b: Int) = a * b // 不会插入到原有的类上

open class AA

fun AA.x() {
    println("a")
}

class BB : AA()

fun BB.x() {
    println("b")
}

fun myPrint(aa: AA) {
    aa.x()
}

fun Any?.toString(): String {
    if (null == this) {
        return "null"
    }
    return toString()
}

fun main() {
    val extentionTest = ExtentionTest()

    println(extentionTest.add(1, 2))
    println(extentionTest.substract(1, 2))

    println(extentionTest.multiply(2, 3))

    myPrint(AA())
    myPrint(BB())
}

