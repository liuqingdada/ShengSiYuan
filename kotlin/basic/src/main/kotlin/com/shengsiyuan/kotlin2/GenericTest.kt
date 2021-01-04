package com.shengsiyuan.kotlin2

class GenericTest2 {
    fun printAnimal(animal: Animal<String>) {
        val aa: Animal<Any> = animal
    }
}

interface Animal<out T> {
    fun getName(): T
}
