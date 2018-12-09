package com.shengsiyuan.kotlin3

/**
 * Created by suhen
 * 18-12-9.
 * Email: 1239604859@qq.com
 *
 * 匿名对象只能在局部变量范围內, 或是被 private 修饰的成员变量范围內才能被识别出真正的类型
 *
 * 如果将匿名对象当做一个 非private 方法的返回值, 或是 非private 属性的类型, 那么该方法或是属性的真正类型就是该匿名对象所声明的父类型;
 * 如果没有声明任何父类型, 那么其类型就是 Any, 在这种情况下, 匿名对象中所声明的任何成员都是无法访问的
 */
class MyClass {
    private val obj = object {
        fun output() {
            println("output invock")
        }
    }

    fun test() {
        println(obj.javaClass)
        println(obj::class.java)
        obj.output()
    }
}

class MyClass2 {

    private fun method() = object {
        val str = "hello"
    }

    internal fun method2() = object {
        val str = "world"
    }

    fun test() {
        val str = method().str

        //val str2 = method2().str
    }
}

fun main() {
    val mc = MyClass()
    mc.test()
}