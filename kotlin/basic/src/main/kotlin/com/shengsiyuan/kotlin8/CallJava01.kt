package com.shengsiyuan.kotlin8

/**
 * Created by yangliuqing
 * 2019-03-10.
 * Email: 1239604859@qq.com
 *
 * 在 Java 中，所有引用都有可能为 null，然而在 Kotlin 中，对 null 是有着严格的检查与限制的，
 * 这就使得来自于 Java 的引用在 Kotlin 中变得不再适合；基于这个原因，在 Kotlin 中，
 * 将来自于 Java 的声明类型称为平台类型（Platform Types）。
 *
 * 对于平台类型来说，Kotlin 的 null 检查就会得到一定的缓和，变得不再那么严格了。
 * 这样就使得空安全的语义要求变得与 Java 一致。
 *
 * 当我们调用平台类型引用方法时，Kotlin 就不会在编译期间施加空安全的检查，使得编译可以正常通过；但是在运行期有可能抛出异常
 * 因为平台类型引用值有可能为 null
 */

fun main() {
    val list = ArrayList<String>()

    list.add("hello")
    list.add("world")
    list.add("hello world")

    for (item in list) {
        println(item)
    }

    for (i in 0 until list.size) {
        println(list[i])
    }

    println("==========")

    val person = Person()
    person.age = 20
    person.isMarried = true
    println(person.name)
    println(person.age)
    println(person.isMarried)

    println("===========")

    val list2 = ArrayList<String>()
    list2.add("hello")

    val size = list2.size
    val item = list2[0] // 允许，运行期可能失败

    val s1: String? = item // 允许，总是可以
    val s2: String = item  // 允许，不过可能会在运行期间失败

    /**
     * 如果我们使用了不可空类型，编译器会在赋值时生成一个断言。这回防止 Kotlin 的不可空变量持有 null 值
     * 同样，这一点也适用于 Kotlin 方法参数传递，我们在将一个平台类型值传递给方法的一个不可空参数时，也会生成一个断言
     * 总体来说，Kotlin 会竭尽所能防止 null 的赋值蔓延到程序的其它地方，而是在发生问题之处就立刻通过断言来解决
     */
}