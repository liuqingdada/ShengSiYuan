package com.shengsiyuan.kotlin8

/**
 * Created by yangliuqing
 * 2019-03-10.
 * Email: 1239604859@qq.com
 *
 * 数组 Array
 *
 * Kotlin 中数组是不变的（相对于协变和逆变来说），这一点和 Java 存在明显的不同
 * 这意味着，我们无法将一个 Array<String> 付给 Array<Any>，这样就可以防止可能出现的运行期异常
 *
 * Kotlin 提供了原生数据类型数组来避免自动装箱与拆箱带来的成本：
 * ByteArray    ->    byte[]
 * CharArray    ->    char[]
 * ShortArray   ->    short[]
 * IntArray     ->    int[]
 * BooleanArray ->    boolean[]
 * FloatArray   ->    float[]
 * LongArray    ->    long[]
 * DoubleArray  ->    double[]
 *
 * 当编译器为 JVM 字节码时，编译器会优化对于数组的访问，使之不会产生额外的成本
 */

fun main() {
    val myArray = MyArray()
    val intArray: IntArray = intArrayOf(1, 2, 3, 4)    // int[]
    val array: Array<Int> = arrayOf(1, 2, 3, 4, 5)     // Integer[]

    myArray.myIntArrayMethod(intArray)
    myArray.myIntegerArrayMethod(array)

    println("========")

    val arr = arrayOf(0, 1, 2, 3, 4)
    arr[0] = arr[0] * 2 // 并不会调用set或是get方法
    for (i in arr) {    // 并不会调用set或是get方法
        println(i)
    }
}