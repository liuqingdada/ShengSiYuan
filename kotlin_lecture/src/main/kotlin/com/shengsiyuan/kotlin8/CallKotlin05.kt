package com.shengsiyuan.kotlin8

/**
 * Created by yangliuqing
 * 2019-03-10.
 * Email: 1239604859@qq.com
 *
 * 属性 Properties
 *
 * 一个 Kotlin 属性会被编译为3部分Java元素
 * 1. 一个 getter 方法
 * 2. 一个 setter 方法
 * 3. 一个私有的字段（field），其名字与 Kotlin 的属性名一样
 *
 * 如果 Kotlin 属性名以 is 开头，那么命名约定会发生一些变化：
 * 1. getter 方法名与属性名一样
 * 2. setter 方法名则是将 is 替换为 set
 * 这种规则适用于任何类型，而不单单是 Boolean 类型
 */

class Test {
    var isStudent: String = "yes"
}