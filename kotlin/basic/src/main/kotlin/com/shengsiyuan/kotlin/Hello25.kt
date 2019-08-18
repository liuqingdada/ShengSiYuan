package com.shengsiyuan.kotlin

/**
 * Created by suhen
 * 18-11-18.
 * Email: 1239604859@qq.com
 */

class CompanionObjectExtention {
    companion object MyObject {

    }
}

fun CompanionObjectExtention.MyObject.method() {
    println("hello world")
}

fun main() {
    CompanionObjectExtention.method()
}