package com.shengsiyuan.kotlin4

import kotlin.properties.Delegates

/**
 * Created by suhen
 * 18-12-11.
 * Email: 1239604859@qq.com
 *
 * 非空属性
 */

class MyPerson {
    var address: String by Delegates.notNull()
}

fun main() {
    val myPerson = MyPerson()
    myPerson.address = "suhen"
    println(myPerson.address)
}
