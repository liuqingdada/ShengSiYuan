package com.shengsiyuan.kotlin2

/**
 * Created by suhen
 * 18-12-7.
 * Email: 1239604859@qq.com
 */

class MyStorage<out T>(private var t: T) {
    fun getValue(): T = this.t

    fun setValue(t: @UnsafeVariance T) {
        this.t = t
    }
}

fun main(args: Array<String>) {
    val myStorage: MyStorage<Int> = MyStorage(5)
    val myStorage2: MyStorage<Any> = myStorage

    println(myStorage2.getValue())

    myStorage2.setValue("hello") // 泛型擦除

    println(myStorage2.getValue())
}