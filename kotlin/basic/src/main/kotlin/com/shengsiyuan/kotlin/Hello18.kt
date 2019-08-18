package com.shengsiyuan.kotlin

/**
 * Created by liuqing
 * 2018/11/8.
 * Email: suhen0420@163.com
 */

open class BaseClass {
    open fun method() {

    }
}

abstract class Sub: BaseClass() {
    abstract override fun method()
}