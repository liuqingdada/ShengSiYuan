package com.shengsiyuan.reflection

import kotlin.reflect.full.createInstance

/**
 * Created by yangliuqing
 * 2019-07-19.
 * Email: 1239604859@qq.com
 */

class Goods(var name: String) {
    var price = 0.0

    constructor() : this("未知商品") {
        this.price = 0.0
    }

    constructor(name: String, price: Double) : this(name) {
        this.price = price
    }

    constructor(name: String, address: String) : this(name) {
        println(address)
    }
}

fun main() {
    val clazz = Goods::class
    val instance = clazz.createInstance()

    println(instance.name)
    println(instance.price)
    println("===========")

    val cons = clazz.constructors
    cons.filter {
        it.parameters.size == 2
    }.filter {
        it.parameters[0].name == "name" && it.parameters[1].name == "price"
    }.forEach {
        val goods = it.call("Java", 100.toLong())
        println(goods.name)
        println(goods.price)
    }

    cons.filter {
        it.parameters.size == 2
    }.filter {
        it.parameters[0].name == "name" && it.parameters[1].name == "address"
    }.forEach {
        val goods = it.call("Kotlin", "Beijing")
        println(goods.name)
        print(goods.price)
    }

}