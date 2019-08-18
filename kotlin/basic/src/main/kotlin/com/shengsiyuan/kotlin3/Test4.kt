package com.shengsiyuan.kotlin3

/**
 * Created by suhen
 * 18-12-9.
 * Email: 1239604859@qq.com
 */

class OuterClass4 {

    class NestedClass4 {
        init {
            println("nested class 4 init")
        }
    }
}

fun main() {
    val nc: OuterClass4.NestedClass4 = OuterClass4.NestedClass4()

}