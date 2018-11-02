package com.shengsiyuan.kotlin

//import com.shengsiyuan.kotlin2.multiply
import com.shengsiyuan.kotlin2.multiply as myMultiply

/**
 * Created by liuqing
 * 2018/11/2.
 * Email: suhen0420@163.com
 */

fun main(args: Array<String>) {
    val a: Int = 1
    // a = 2 // error
    val b = 2

    var c: Int = 3
    c = 4

    var d = 3;
    d = 4

    println("$a, $b, $c, $d")

    /**
     *
     */

    /*
        /*
         */
     */

    var x = 10
    val y: Byte = 20

    //x = y // error
    x = y.toInt()

    println(x)

    println(myMultiply(2, 3))


}