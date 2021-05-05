package com.shengsiyuan.kotlin

import org.junit.Test
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

/**
 * Created by liuqing
 * 2018/11/9.
 * Email: suhen0420@163.com
 */

class Code {
    @Test
    fun test() {
        val runtime = Runtime.getRuntime()
        val maxMemory = runtime.maxMemory() / 1024 / 1024
        val totalMemory = runtime.totalMemory() / 1024 / 1024
        val freeMemory = runtime.freeMemory() / 1024 / 1024
        val used = totalMemory - freeMemory

        println(maxMemory)
        println(totalMemory)
        println(freeMemory)
        println(used)

    }

    @Test
    fun ifNotNull() {
        val file = File("/Users/andy")
        println(file.path)

        val files = file.listFiles()
        files?.forEach { println("\t${it.path}") }

        val str: String? = null
        println(str?.length)
        println(str!!.length)
    }

    @Test
    fun ifNotNullAndElse() {
        val files = File("/Users/andy").listFiles()
        println(files.javaClass)

        val dirs = files ?: arrayOf(File(""))
        println(dirs.javaClass)
        dirs.forEach { println(it.name) }

        val size = files?.size ?: 0
        println(size)
    }

    /*
     *  ifNotNull    ?:    ifNull
     */

    @Test
    fun ifNull() {
        val value = null
        val ret = value ?: "value is null"
        println(ret)
    }

    @Test
    fun tryWithResources() {
        val stream = Files.newInputStream(Paths.get("/Users/andy/Desktop/json.txt"))
        stream.buffered().reader().use { reader ->
            println(reader.readText())
        }
    }

    @Test
    fun safeInvok() {
        val bob: String? = null

        val len = bob?.toString()?.toString()?.length
        println("TextView.setText($len)")  // 依然不安全，因为如果任意一个属性(环节)为空，这个链式调用就会返回 null。

        bob?.toString()?.toString()?.length?.let {
            // 安全的原因在于把 length 重新加了安全调用
            println("TextView.setText($len)")
        }
    }

    @Test
    fun elvis() {
        val a: String? = null
        val b: Int? = null
        val m = if (b == null) -1 else b  // 复杂
        val n = b ?: -1

//        foo(null)
//        foo("")

//        val l = b!!
//        val k = a!!.length

        // 如果对象不是目标类型，那么常规类型转换可能会导致 ClassCastException
        // as 失败抛异常 as? 失败返回null
        // 另一个选择是使用 安全的类型转换，如果尝试转换不成功则返回 null:
        val safe: Int? = a as? Int
    }

    private fun foo(data: String?): String? {
        val name = data ?: throw IllegalArgumentException("name expected")
        return null
    }

    @Test
    fun nullableCollection() {
        val nullList: List<String?> = listOf("23", null)

        val noNullList1: List<String> = nullList.requireNoNulls()
        val noNullList2: List<String> = nullList.filterNotNull()
    }

    @Test
    fun number() {
        println(Int.MAX_VALUE)
        println(Int.MIN_VALUE)
        val i: Int = Int.MAX_VALUE + 1
        println(i)
    }
}