package com.shengsiyuan.kotlin

/**
 * Created by liuqing
 * 2018/11/7.
 * Email: suhen0420@163.com
 */

class Student(private val username: String, private val age: Int, private val address: String) {

    fun printInfo() {
        println("Student(username='$username', age='$age', address='$address')")
    }
}

// 默认值
class Test(val username: String = "suhen")
// 如果primary构造方法的所有参数都有默认值，那么编译器会生成一个不带参数的构造方法，这个构造方法会使用这些默认值

fun main() {
    val student = Student("zhangsan", 20, "ShenZhen")
    student.printInfo()

    println(Test().username)
}

