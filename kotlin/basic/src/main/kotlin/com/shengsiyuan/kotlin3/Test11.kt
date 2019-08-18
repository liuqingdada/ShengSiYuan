package com.shengsiyuan.kotlin3

import java.awt.event.WindowEvent
import java.awt.event.WindowListener

/**
 * Created by suhen
 * 18-12-10.
 * Email: 1239604859@qq.com
 *
 * 对象表达式是立刻初始化或是执行的
 * 对象声明是延迟初始化的, 在首次访问的时候进行
 * 伴生对象是在其所在的类被加载的时候初始化的, 对应于Java的静态初始化
 */

object MyObject {
    fun method() = "hello world"
}

object MyObject2 : WindowListener {
    override fun windowDeiconified(e: WindowEvent?) {
    }

    override fun windowClosing(e: WindowEvent?) {
    }

    override fun windowClosed(e: WindowEvent?) {
    }

    override fun windowActivated(e: WindowEvent?) {
    }

    override fun windowDeactivated(e: WindowEvent?) {
    }

    override fun windowOpened(e: WindowEvent?) {
    }

    override fun windowIconified(e: WindowEvent?) {
    }

    fun method() = "welcome"
}

fun main() {
    println(MyObject.method())

    val obj = MyObject2
    println(obj.method())
}