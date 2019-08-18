package com.shengsiyuan.kotlin8

/**
 * Created by yangliuqing
 * 2019-03-10.
 * Email: 1239604859@qq.com
 */

class Person2 {
    var name: String = "suhen"

    /**
     * @JvmField 对 Kotlin 中的属性进行标注时，表示他是一个实例字段（instance field）
     * Kotlin 编译器在处理的时候，将不会给这个字段生成 getter/setter
     */
    @JvmField
    val age: Int = 20
}