package com.shengsiyuan.kotlin.func

import com.shengsiyuan.DigestEncodingUtils
import org.junit.Test
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

/**
 * 中缀表示法：
 * 标有 infix 关键字的函数也可以使用中缀表示法(忽略该调用的点与圆括号)调用。中缀函数必须满足 以下要求:
 *   — 它们必须是成员函数或扩展函数;
 *   — 它们必须只有一个参数;
 *   — 其参数不得接受可变数量的参数且不能有默认值。
 */
infix fun Int.sh1(x: Int): Int { // 全局的函数
    var ret = this + x
    try {
        val md5 = DigestEncodingUtils.md5(ret.toString())
        println(md5)
        md5.toCharArray().forEach {
            ret += it.toInt()
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return ret
}

inline fun <T> lock(lock: Lock, body: () -> T): T {
    lock.lock()
    try {
        return body()
    } finally {
        lock.unlock()
    }
}

class FuncTest {
    private val reentrantLock = ReentrantLock()

    private val eps = 1E-10 // "good enough", could be 10^-15

    infix fun add(s: String) = "FUNC: $s"

    @Test
    fun infixTest() {
        val ret = 1 sh1 2
        println(ret)

        val s1 = add("123")
        val s2 = this add "456"

    }

    /**
     * 尾递归函数
     * Kotlin 支持一种称为尾递归的函数式编程⻛格。这允许一些通常用循环写的算法改用递归函数来写，而 无堆栈溢出的⻛险。
     * 当一个函数用 tailrec 修饰符标记并满足所需的形式时，编译器会优化该递归， 留下一个快速而高效的基于循环的版本:
     */
    private tailrec fun findFixPoin(x: Double = 1.0): Double =
            if (Math.abs(x - Math.cos(x)) < eps) {
                x
            } else {
                val tmp = Math.cos(x)
                println(tmp)
                Thread.sleep(100)
                findFixPoin(tmp)
            }

    @Test
    fun tailrecTest() {
        val ret = findFixPoin()
        println(ret)
    }

    @Test
    fun inlineTest() {
        val str = "123"
        val len = lock(reentrantLock) {
            str.length
        }
        println(len)
    }
}
