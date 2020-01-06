package com.shengsiyuan.kotlin.io

import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.net.URL
import kotlin.test.Test

/**
 * Created by andy
 * 2019-11-21.
 * Email: 1239604859@qq.com
 */
class IoTest {
    private val bigFile = File("/Users/andy/Downloads/Microsoft Office 2019 16.17.rar")
    private val gradleFile = File("build.gradle")

    @Test
    fun readFile() {
        val inputStream: InputStream = FileInputStream(gradleFile)
        inputStream.bufferedReader().forEachLine { line ->
            println(line)
        } // stram close
    }

    @Test
    fun readBytes() {
        FileInputStream(bigFile).readBytes().forEach {
            println(it)
        }

        bigFile.forEachBlock { buffer, bytesRead ->
            println("size: $bytesRead --> ${buffer.contentToString()}")
        }
    }

    @Test
    fun walk() {
        bigFile.walk().iterator().forEach {
            println(it)
        }
        gradleFile.absoluteFile.parentFile.walk().forEach {
            println(it)
        }
    }

    @Test
    fun networkIo() {
        val baidu = "https://www.baidu.com"
        val url = URL(baidu)
        val text = url.readText()
        println(text)
    }
}
