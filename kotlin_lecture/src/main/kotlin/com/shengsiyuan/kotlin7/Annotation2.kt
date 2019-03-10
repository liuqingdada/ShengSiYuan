// 默认是 Annotation2Kt.class
@file: JvmName("SuhenAnnotation")

package com.shengsiyuan.kotlin7

import kotlin.reflect.KClass

/**
 * Created by yangliuqing
 * 2019-03-03.
 * Email: 1239604859@qq.com
 *
 *  注解也可以拥有自己的构造方法，并且构造方法也可以接收参数
 *  注解构造方法所允许的参数类型：
 *  1，与 Java 原声类型所对应的类型（Int，Long ...）
 *  2，String 字符串
 *  3，classes （Myclass::class）
 *  4，枚举
 *  5，其它的注解
 *  6，上述类型的数组类型
 *
 *  Kotlin 的注解参数是不允许为可空类型的，因为JVM不支持 null 的形式存储注解属性值的
 *
 *  如果某个注解被用作其它注解的参数，那么其名字就不需要以 @ 符号开头
 */

annotation class Annotation1(val str: String)

@Annotation1("hello")
class Class1

annotation class Annotation2(val str: String, val ann: Annotation1)

@Annotation2("world", Annotation1("hello"))
class Class2

/**
 * 如果需要将某个 class 作为注解的参数，那么请使用 Kotlin class （KClass）
 * Kotlin 编译器会自动将其转换为 java class
 * 这样，java 代码就可以正常看到注解与参数了
 */

annotation class Annotation3(val arg1: KClass<*>, val arg2: KClass<out Any>)

@Annotation3(String::class, Int::class)
class Class3

/**
 * 注解使用处目标  use-site target
 *
 * 在对类的属性或是主构造方法的参数声明注解时，会存在多个 Java 元素都可以通过对应的 Kotlin 元素生成出来，
 * 因此，在所生成的 Java 字节码中，就会存在多个可能的位置来生成相应的注解。若想精确指定如何来生成注解，
 * 那么可以使用注解的使用处目标方式来实现。
 */

class Class4(@field: MyAnnotation val arg1: String,    // 注解 Java field
             @get: MyAnnotation val arg2: String,      // 注解 Java getter
             @param: MyAnnotation val arg3: String)    // 注解 Java 构造方法参数

fun main() {
    println("kt?")
}