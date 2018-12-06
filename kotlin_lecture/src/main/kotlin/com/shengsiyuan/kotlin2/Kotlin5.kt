package com.shengsiyuan.kotlin2

/**
 * Created by suhen
 * 18-12-6.
 * Email: 1239604859@qq.com
 */

/*
    Kotlin: 声明处协变; Java: 使用处协变

    Kotlin 中的 out 关键字叫做 variance annotation, 因为他是在类型参数声明处所指定的,
    因此我们称之为声明处协变(declaration-site variance)

    对于Java来说则是使用处协变(use-site variance), 其中 类型通配符<? extends> <? super> 使得类型协变成为可能
 */

/**
 * 协变
 * 如果泛型类只是将泛型类型作为其方法的输出类型, 那么我们就可以使用 out
 * produce = output = out
 */
interface Producer<out T> {
    fun produce(): T
}

/**
 * 逆变
 * 如果泛型类只是将泛型类型作为其方法的输入类型, 那么我们就可以使用 in
 * consume = input = in
 */
interface Consumer<in T> {
    fun consume(item: T)
}

/**
 * 不变
 * 如果泛型类同时将泛型类型作为其方法的输入与输出类型, 那么我们就不能使用 out 和 in 来修饰泛型
 */
interface ProducerConsumer<T> {
    fun produce(): T

    fun consume(item: T)
}


open class Fruit

open class Apple : Fruit()

class ApplePear : Apple()


class FruitProducer : Producer<Fruit> {
    override fun produce(): Fruit {
        println("produce a fruit")
        return Fruit()
    }
}

class AppleProducer : Producer<Apple> {
    override fun produce(): Apple {
        println("produce a apple")
        return Apple()
    }
}

class ApplePearProducer : Producer<ApplePear> {
    override fun produce(): ApplePear {
        println("produce a apple pear")
        return ApplePear()
    }
}

//

class Human : Consumer<Fruit> {
    override fun consume(item: Fruit) {
        println(item)
    }
}

class Man : Consumer<Apple> {
    override fun consume(item: Apple) {
        println(item)
    }
}

class Boy : Consumer<ApplePear> {
    override fun consume(item: ApplePear) {
        println(item)
    }
}

fun main() {
    val p1: Producer<Fruit> = FruitProducer()
    val p2: Producer<Fruit> = AppleProducer()
    val p3: Producer<Fruit> = ApplePearProducer()

    val p: Producer<Any> = ApplePearProducer()


    val c1: Consumer<ApplePear> = Human()
    val c2: Consumer<ApplePear> = Man()
    val c3: Consumer<ApplePear> = Boy()
}
