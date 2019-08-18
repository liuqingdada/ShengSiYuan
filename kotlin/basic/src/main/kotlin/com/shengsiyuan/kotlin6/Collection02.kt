package com.shengsiyuan.kotlin6

/**
 * Created by yangliuqing
 * 2019-03-03.
 * Email: 1239604859@qq.com
 *
 * Kotlin 严格区分可变集合与不可变集合
 * 要清楚一点的是：区分开可变集合的只读视图与实际上真正的不可变集合
 */

fun main() {
    val stringList: MutableList<String> = mutableListOf("hello", "world", "hello world")
    val readOnlyList: List<String> = stringList

    println(stringList)

    stringList.add("welcome")

    println(readOnlyList)
    //readOnlyList.clear()

    println("---------")

    val strings: HashSet<String> = hashSetOf("a", "b", "c", "c")
    println(strings.size)

    println("--------")

    // 只读类型的集合是协变的，因为它只用于从集合中获取数据，而不会修改集合中的数据
    val s1 = listOf("a", "b")
    val s2: List<Any> = s1

    println("---------")

    // 快照 Snapshoot
    // 比如，toList 方法只是复制原来集合中的元素，所以返回的集合就可以确保不会发生变化

    val items1 = mutableListOf<String>("a", "b", "c")
    val items2 = items1.toList()

    items1.add("d")

    println(items1)
    println(items2)


}