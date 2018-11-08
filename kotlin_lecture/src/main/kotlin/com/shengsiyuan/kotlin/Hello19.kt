package com.shengsiyuan.kotlin

/**
 * Created by liuqing
 * 2018/11/8.
 * Email: suhen0420@163.com
 */

// object declaration, 对象声明

object MyObject {

    fun method() {
        println("method")
    }
}

// campanion object, 伴生对象
// 类没有static方法
// 大多数情况下，推荐使用包级别的函数来作为静态方法，kotlin会将包级别的函数当作静态方法来看待

// 不提供companion object的名字，编译器会提供一个默认名字 -- Companion

// companion object看起来像Java的静态成员，但是运行期他们依旧是真实对象的实例成员
// JVM上可以将companion object的成员真正生成为类的静态方法和属性，通过@JvmStatic注解实现

class MyTest {
    companion object CO {
        val a: Int = 100

        @JvmStatic
        fun method() {
            println("method invocked")
        }
    }
}

class D {
    companion object {
        @JvmStatic
        fun foo() {

        }

        fun bar() {

        }
    }
}

fun main() {
    MyObject.method()

    MyTest.CO.method()
    MyTest.method()

    val v = MyTest.CO
    println(v.javaClass)
}
