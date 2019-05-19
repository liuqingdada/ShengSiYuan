package com.shengsiyuan.reflection

import java.io.Serializable
import kotlin.reflect.full.allSuperclasses
import kotlin.reflect.full.superclasses

/**
 * Created by yangliuqing
 * 2019-05-19.
 * Email: 1239604859@qq.com
 */

interface M

open class P : M

class MySerializable : P(), Serializable

fun main() {
    val kclass = MySerializable::class
    println(kclass.superclasses)
    println(kclass.allSuperclasses)
}