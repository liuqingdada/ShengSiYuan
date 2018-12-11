package com.shengsiyuan.kotlin4

/**
 * Created by suhen
 * 18-12-10.
 * Email: 1239604859@qq.com
 *
 * 委托 delegation
 */

interface MyInterface {
    fun myPrint()
}

interface MyInterface2 {
    fun myPrint()
}

class MyInterfaceImpl(private val str: String) : MyInterface, MyInterface2 {
    override fun myPrint() {
        println("hello: $str")
    }
}

/**
 * by 关键字后面的对象实际上会被存储在类的内部, 编译器则会把父接口的所有方法实现出来, 并且将实现转移给委托对象去进行
 */
class MyClass(private val myInterface: MyInterfaceImpl) : MyInterface, MyInterface2 by myInterface {
//    override fun myPrint() {
//        myInterface.myPrint()
//        println("hello world")
//    }
}

fun main() {
    val myInterfaceImpl = MyInterfaceImpl("suhen")
    val myClass = MyClass(myInterfaceImpl)
    myClass.myPrint()
}