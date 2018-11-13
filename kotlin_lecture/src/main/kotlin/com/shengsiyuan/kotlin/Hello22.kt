package com.shengsiyuan.kotlin

/**
 * Created by liuqing
 * 2018/11/13.
 * Email: suhen0420@163.com
 */

// 可见性 visibility
// private, protected, internal, public


private fun method0() {
}

private open class Clazz0 {
    protected open val b = 3

    internal val c = 1
}

// private public 和 Java 特别类似

// internal: 只能在同一模块下使用

// protected: 不能用在 顶层 的 函数 和 类; 可以用在类的属性和方法上

object O {
    private val n: String = ""
    //protected val m: String = ""
    public val b: String = ""
}
