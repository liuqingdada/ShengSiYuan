package com.shengsiyuan.kotlin3

/**
 * Created by suhen
 * 18-12-9.
 * Email: 1239604859@qq.com
 */

class OuterClass5 {

    inner class InnerClass5(str: String) {
        init {
            println(str)
        }
    }
}

fun main() {
    val ic: OuterClass5.InnerClass5 = OuterClass5().InnerClass5("hello")
}