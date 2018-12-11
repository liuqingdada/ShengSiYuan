package com.shengsiyuan.kotlin4

/**
 * Created by suhen
 * 18-12-11.
 * Email: 1239604859@qq.com
 *
 * 延迟属性
 * 指的是属性只在第一次被访问的时候才会计算, 之后则会将之前的计算结果缓存起来供后续调用
 */

val myLazyValue: Long by lazy(LazyThreadSafetyMode.PUBLICATION) {
    println("hello")

    System.nanoTime()
}

fun main() {
    for (i in 1..100) {
        Thread {
            Thread.sleep(300)
            println(myLazyValue)
        }.start()
    }
}