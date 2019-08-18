package com.shengsiyuan.kotlin5

/**
 * Created by yangliuqing
 * 2019-01-27.
 * Email: 1239604859@qq.com
 */

fun kotlinCalculate(a: Int, b: Int, calculate: (Int, Int) -> Int) {
    println(calculate(a, b))
}

fun main() {
    kotlinCalculate(2, 3) { a, b ->
        a + b
    }

    kotlinCalculate(2, 3) { a, b ->
        a * b
    }
}