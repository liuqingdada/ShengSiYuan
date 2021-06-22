package com.leetcode.core

import java.util.*

/**
 * Created by cooper
 * 2021/5/10.
 * Email: 1239604859@qq.com
 */
class StringTest {
    fun getSubStrings(str: String): List<String> {
        val chars = str.toCharArray()
        val queue = LinkedList<Char>()
        val list = arrayListOf<String>()

        for (i in chars.indices) {
            for (c in chars) {
                queue.offer(c)
            }
            queue.remove(chars[i])

            val sb = StringBuilder()
            while (queue.isNotEmpty()) {
                val head = queue.poll()
                sb.append(head)
            }
            list.add(sb.toString())

            queue.clear()
        }
        return list
    }
}

fun main() {
    val test = StringTest()
    test.getSubStrings("abcde").forEach {
        println(it)
    }
}
