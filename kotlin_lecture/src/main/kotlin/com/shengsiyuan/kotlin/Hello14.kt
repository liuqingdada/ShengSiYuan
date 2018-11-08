package com.shengsiyuan.kotlin

/**
 * Created by liuqing
 * 2018/11/8.
 * Email: suhen0420@163.com
 */

// 所有类在默认情况下都无法被继承
// 换句话说，所有类默认情况下都是final的

open class Parent(name: String, age: Int) {

}

class Child(name: String, age: Int) : Parent(name, age) {

}

open class Parent2(name: String) {

}

class Child2 : Parent2 {

    constructor(name: String) : super(name)
}
