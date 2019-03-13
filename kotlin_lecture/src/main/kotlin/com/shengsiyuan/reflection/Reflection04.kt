package com.shengsiyuan.reflection

/**
 * Created by yangliuqing
 * 2019-03-13.
 * Email: 1239604859@qq.com
 *
 *  函数组合
 */

fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C {
    return { x -> f(g(x)) }
}

fun isEvenNum(x: Int) = 0 == x % 2

fun length(s: String) = s.length

fun main() {
    val evenLength: (String) -> Boolean = compose<String, Int, Boolean>(::isEvenNum, ::length)

    val strings = listOf<String>("a", "ab", "abc", "abcd", "abcde")

    /**
     * val evenLength: (String) -> Boolean
     *
     * filter方法:
     * predicate: (T) -> Boolean
     */
    val list = strings.filter(evenLength)
    println(list)
}