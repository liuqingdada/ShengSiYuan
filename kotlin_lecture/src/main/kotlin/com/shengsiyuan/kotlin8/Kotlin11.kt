package com.shengsiyuan.kotlin8

/**
 * Created by yangliuqing
 * 2019-03-10.
 * Email: 1239604859@qq.com
 *
 * 在 Kotlin 中，我们可以将具名对象或是半生对象中定义的函数注解为 @JvmStatic，这样编译器既会在相应对象的类中生成静态方法，
 * 也会在对象自身中生成实例方法
 */

class ObjectTest {

    companion object {
        fun test1() {
            println("test1")
        }

        @JvmStatic
        fun test2() {
            println("test2")
        }
    }
}