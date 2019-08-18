package com.shengsiyuan.kotlin

/**
 * Created by liuqing
 * 2018/11/13.
 * Email: suhen0420@163.com
 */

class TheClass {

    /**
     * 延迟初始化属性
     *
     * Kotlin 要求非空类型的属性必须在构造方法中初始化
     * 有时，这种要求不太方便，比如可以在依赖注入或者单元测试的情况下进行属性赋值
     *
     * 通过 lateinit 标识属性为延迟初始化，需要满足三个条件：
     *  1. lateinit 只能用在类体声明的 var 属性上，不能用在 primary constructor 声明的属性上
     *  2. 属性不能拥有自定义的 setter 和 getter
     *  3. 属性类型需要为非空，并且不能是原生数据类型
     *
     */
    lateinit var name: String

    fun init() {
        name = "zhangsan"
    }

    fun printName() {
        println(name)
    }
}

fun main() {
    val theClass = TheClass()

    theClass.printName()
}

