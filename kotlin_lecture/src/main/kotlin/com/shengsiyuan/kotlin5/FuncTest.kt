package com.shengsiyuan.kotlin5

/**
 * Created by yangliuqing
 * 2019-01-20.
 * Email: 1239604859@qq.com
 *
 * 默认参数 default argument
 *
 * 对于重写的方法来说，子类所拥有的重写方法会使用父类相同的默认参数值
 * 在重写一个拥有默认参数值的方法时，方法签名中必须要将默认参数值省略掉
 *
 * 如果一个默认参数位于其他无默认的参数前面，那么默认值只能通过在调用函数时使用具名参数的方式来使用
 */

fun test(a: Int = 0, b: Int = 1) = println(a - b)

open class A {
    open fun method(a: Int, b: Int = 1) = a + b
}

class B : A() {
    override fun method(a: Int, b: Int): Int = a - b
}

fun test2(a: Int = 1, b: Int = 2, compute: (x: Int, y: Int) -> Unit) {
    compute(a, b)
}

fun main() {
    test()
    test(2)
    test(b = 2) // 显示指定参数名
    test(a = 2, b = 1)

    println("-------------")

    val a = A()
    val b = B()
    println(a.method(0))
    println(b.method(0))

    println("-------------")

    test2 { x, y -> println(x * y) }
    test2(1, 2, ::test)
}