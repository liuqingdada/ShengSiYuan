package com.shengsiyuan.netty.bytebuff

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater

/**
 * Created by andy
 * 2019-03-18.
 * Email: 1239604859@qq.com
 */

fun main() {
//    common()
    atomic()
}

/**
 * 即使用 @Volatile 也无法避免，因为是多个线程同时写
 */
fun common() {
    val person = Person()
    for (i in 0 until 10) {
        Thread {
            Thread.sleep(1000)
            println(person.getAndAdd(1))
        }.start()
    }
}

fun atomic() {
    val person = Person()

    for (i in 1 until 10) {
        Thread {
            Thread.sleep(1000)
            println(person.getAndAdd(1))
        }.start()
    }
}

class Person {
    private companion object {
        private val FU = AtomicIntegerFieldUpdater.newUpdater(Person::class.java, "age")
    }

    @Volatile
    private var age: Int = 1

    fun getAndAdd(i: Int): Int = FU.getAndAdd(this, i)
}