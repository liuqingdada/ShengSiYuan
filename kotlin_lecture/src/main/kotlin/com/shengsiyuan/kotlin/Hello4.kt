package com.shengsiyuan.kotlin

/**
 * Created by liuqing
 * 2018/11/2.
 * Email: suhen0420@163.com
 */

fun main(args: Array<String>) {
    println(convert2Int("ab"))
}

fun convert2Int(str: String): Int? {
    return try {
        str.toInt()
    } catch (e: NumberFormatException) {
        e.printStackTrace()
        null
    }
}

fun printMultiply (a:String, b: String) {
    val aa = convert2Int(a)
    val bb = convert2Int(b)

    // println(aa * bb) // error
}
