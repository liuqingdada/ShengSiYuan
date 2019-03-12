package com.shengsiyuan.kotlin9

/**
 * Created by yangliuqing
 * 2019-03-12.
 * Email: 1239604859@qq.com
 *
 * @JvmOverloads primary constructor:
 *      Data(int m, int n)
 *      Data(int m, int n, int x)
 *      Data(int m, int n, int x, String y)
 *      Data(int m, int n, int x, String y, String z)
 */

class Data @JvmOverloads constructor(m: Int, n: Int, x: Int = 1, y: String = "hello", z: String = "world") {

    fun method(a: Int, b: String, c: Int = 2) {
        println("a: $a, b: $b, c: $c")
    }
}
