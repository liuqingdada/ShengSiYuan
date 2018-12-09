package com.shengsiyuan.kotlin3

/**
 * Created by suhen
 * 18-12-9.
 * Email: 1239604859@qq.com
 */

class Person(val name: String, var age: Int) {

    private inner class PersonFeature(var height: Int, var weight: Int) {
        fun getPersonFeature() {
            println("height: $height, weight: $weight")

            this@Person.method()
        }
    }

    private fun method() {
        println("exec Person's method")
    }

    fun getPerson() {
        val personFeature = PersonFeature(180, 66)
        personFeature.getPersonFeature()
    }
}

fun main() {
    val person = Person("zhangsan", 20)
    person.getPerson()
}
