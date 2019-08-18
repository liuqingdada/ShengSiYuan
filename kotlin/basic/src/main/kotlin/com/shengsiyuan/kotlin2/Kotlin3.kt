package com.shengsiyuan.kotlin2

/**
 * Created by suhen
 * 18-11-18.
 * Email: 1239604859@qq.com
 */

// generics 泛型 变量类型的参数化

class MyGeneric<T>(val t: T)

/*
协变 covariant
逆变 controvariant
概念和来源

在Java中
List<String> list = new ArrayList<>();
List<Object> list2 = list // 编译失败

list2.add(new Date());
String str = list.get(0); // ...

所以:
List<? extends Object> list ...

//

interface Collection<E> {
    void addAll(Collection<? extends E> items);
}

void copyAll(Collection<Object> to, Collection<String> from) {
    to.addAll(from);
}

//

如果只从中读取数据,  而不往里面写入数据,那么这样的对象叫做生产者
如果只往里面写入数据,  而不从中读取数据,那么这样的对象叫做消费者

生产者使用 ? extends T     ---- 协变

消费者使用 ? super T       ---- 逆变

PECS: Producer Extends, Consumer Super

*/

fun main() {
    val mg = MyGeneric("hello world")
    println(mg.t)

    val obj = MyClass<String, Number>("abc", 3.14)
    myTest(obj)
}

class MyClass<out T, in M>(t: T, m: M) {
    private val t: T
    private var m: M

    init {
        this.t = t
        this.m = m
    }

    fun get(): T = this.t

    fun set(m: M) {
        this.m = m
    }
}

fun myTest(myClass: MyClass<String, Number>) {
    val obj: MyClass<String, Int> = myClass
    println(obj.get())

    val any: MyClass<Any, Int> = myClass
    println(any.get())
}
