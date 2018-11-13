package com.shengsiyuan.kotlin2

/**
 * Created by suhen
 * 18-11-18.
 * Email: 1239604859@qq.com
 */

// 数据类 - data class
// lombok

/**
 * 1. 主构造方法至少要有一个参数
 * 2. 所有的主构造方法的参数都需要被标记为 val 或者 var
 * 3. 数据类不能是 抽象的、 open的、 sealed的 以及 inner的
 *
 * 对于数据类, 编译器会自动生成如下内容:
 * 1. equals 和 hashCode
 * 2. toString方法 copy方法
 * 3. 针对属性的 componentN 方法并且是按照属性的声明顺序来生成的
 *
 * 关于数据类成员的继承:
 * 1. 如果数据类中显式的定义了 eqauls, hashCode, toString, 或者是在数据类的父类中将这些方法声明为 final, 那么这些方法就不会再生成, 转而使用已有的
 * 2. 如果父类拥有 componentN 方法, 并且是 open 的以及返回兼容的类型, 那么编译器就会在数据类中生成相应的 componentN 方法, 并且重写父类的这些方法; 如果父类中的这些方法是由于不兼容的签名或是被定义为 final 的, 那么编译器就会报错
 * 3. 在数据类中显式的提供 componentN 方法以及 copy 方法实现是不允许的, 编译器报错
 *
 * 解构声明
 * 在主构造方法中有多少个参数, 就会在依次生成对应的 component1 component2 component3 ... ...
 * 这些方法的返回值就是对应字段的值, componentN 方法是用来实现解构声明的
 *
 * 如果生成的类需要无参构造方法, 那么就需要为所有属性指定默认值
 */
data class Person(val name: String, var age: Int, var address: String) {
    fun test(a: Int = 0, b: Int = 0) {
        println(a + b)
    }
}

data class Person2(val name: String = "", var age: Int = 0, val address: String = "")

fun main() {
    val person = Person("suhen", 20, "BeiJing")
    println(person)

    person.test(b = 10)

    val person2 = person.copy(age = 23)
    println(person2)

    val (name, age, address) = person // component 依次赋值
    println("$name, $age, $address")
}
