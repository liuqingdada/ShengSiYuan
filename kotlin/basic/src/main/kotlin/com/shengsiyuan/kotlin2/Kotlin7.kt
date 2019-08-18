package com.shengsiyuan.kotlin2

/**
 * Created by suhen
 * 18-12-6.
 * Email: 1239604859@qq.com
 */

// star projection 星投影

// Star<out T>: 如果 T 的上界是 TUpper, 那么 Start<*> 就相当于 Start<out T>
// 这表示当 T 的类型是未知时, 你可以从 Star<*> 中安全地读取 TUpper 类型的值

// Star<in T>: Star<*> 就相当于 Start<in Nothing>, 这表示无法向其中写入任何值

// Star<T>, 如果 T 的上界为TUpper, 那么 Star<*> 就相当于读取时的 Star<out TUpper>以及写入时的 Star<in Nothing>


class Star<out T>

class Star2<in T> {
    fun setValue(t: T) {}
}

class Star3<T>(private var t: T) {
    fun setValue(t: T) {}

    fun getValue(): T = this.t
}

fun main() {
    val star: Star<Number> = Star<Int>()
    val star2: Star<*> = star


    val star3: Star2<Int> = Star2<Number>()
    val star4: Star2<*> = star3
//    star4.setValue()


    val star5: Star3<String> = Star3("hello")
    val star6: Star3<*> = star5
    star6.getValue()
//    star6.setValue()

    val list: MutableList<*> = mutableListOf("4", "2", "3")
    println(list)
//    list.set(0, "test")
//    list[0] = "test"


}
