package com.shengsiyuan.nio

import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.IntBuffer
import java.security.SecureRandom
import java.util.*
import java.util.stream.Stream

/**
 * Created by andy
 * 2019-03-15.
 * Email: 1239604859@qq.com
 */

fun main() {
    val intBuffer = IntBuffer.allocate(10)
    Stream.generate { SecureRandom().nextInt(100) }
            .limit(10)
            .forEach { intBuffer.put(it) }
    intBuffer.flip()
    while (intBuffer.hasRemaining()) {
        println(intBuffer.get())
    }

    println("--------------")

    intBuffer.flip()
    while (intBuffer.hasRemaining()) {
        println(intBuffer.get())
    }

    println("==========")
    val byteArray: ByteArray = byteArrayOf(1, 0, 0, 0)
    val i = ByteBuffer.wrap(byteArray).order(ByteOrder.LITTLE_ENDIAN).int
    println(i)

    val intBytes = ByteArray(4)
    ByteBuffer.wrap(intBytes).order(ByteOrder.LITTLE_ENDIAN).putInt(i)
    println(Arrays.toString(intBytes))
}