package com.shengsiyuan.kotlin

import kotlin.reflect.KClass
import kotlin.reflect.KProperty0
import kotlin.reflect.KProperty1

/**
 * Created by yangliuqing
 * 2019-01-23.
 * Email: 1239604859@qq.com
 */

class Fragment {
    companion object {
        fun foo() {
            println("this::class -> ${this::class}")
            println("this::class.java -> ${this::class.java}")

            println("this::javaClass -> ${this::javaClass}")
            println("this::javaClass.name -> ${this::javaClass.name}")

            println("this.javaClass -> ${this.javaClass}")
            println("this.javaClass.name -> ${this.javaClass.name}")

            println("Fragment::class -> ${Fragment::class}")
            println("Fragment::class.java -> ${Fragment::class.java}")

            println("Fragment::javaClass -> ${Fragment::javaClass}")
            println("Fragment::javaClass.name -> ${Fragment::javaClass.name}")

            println("Fragment.javaClass -> ${Fragment.javaClass}")
            println("Fragment.javaClass.name -> ${Fragment.javaClass.name}")
        }
    }

    fun run() {
        val kClass: KClass<out Fragment> = this::class
        val clazz: Class<out Fragment> = this::class.java
        val kClass2: KClass<out Fragment> = Fragment::class
        val clazz2: Class<out Fragment> = Fragment::class.java
        val javaClass: Class<out Fragment> = this.javaClass
        val className: String = this.javaClass.name

        val kProperty0: KProperty0<Class<Fragment>> = this::javaClass
        val kProperty1: KProperty1<Fragment, Class<Fragment>> = Fragment::javaClass

        val kProperty0Name: String = this::javaClass.name
        val kProperty1Name = Fragment::javaClass.name

        val clazz3: Class<Companion> = Fragment.javaClass
        val companionName: String = Fragment.javaClass.name

        println("this::class -> ${this::class}")
        println("this::class.java -> ${this::class.java}")
        println("Fragment::class -> ${Fragment::class}")
        println("Fragment::class.java -> ${Fragment::class.java}")
        println("this.javaClass -> ${this.javaClass}")
        println("this.javaClass.name -> ${this.javaClass.name}")
        println("\n")

        println("this::javaClass -> ${this::javaClass}")
        println("Fragment::javaClass -> ${Fragment::javaClass}")
        println("\n")

        println("this::javaClass.name -> ${this::javaClass.name}")
        println("Fragment::javaClass.name -> ${Fragment::javaClass.name}")
        println("\n")

        println("Fragment.javaClass -> ${Fragment.javaClass}")
        println("Fragment.javaClass.name -> ${Fragment.javaClass.name}")
    }
}

fun main() {
    val fragment = Fragment()
    fragment.run()

    println("==========")

    Fragment.foo()
}