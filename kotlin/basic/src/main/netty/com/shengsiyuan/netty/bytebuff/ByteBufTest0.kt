package com.shengsiyuan.netty.bytebuff

import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled

/**
 * Created by andy
 * 2019-03-18.
 * Email: 1239604859@qq.com
 */

private fun bufferStatus(buffer: ByteBuf) {
    println("**********************")
    println(buffer)
    println("isDirect: ${buffer.isDirect}")
    println("isReadOnly: ${buffer.isReadOnly}")
    println("isReadable: ${buffer.isReadable}")
    println("isWritable: ${buffer.isWritable}")
    println("**********************")
}

fun main() {
    val buffer = Unpooled.buffer(10)
    bufferStatus(buffer)

    for (i in 0 until buffer.capacity()) {
        buffer.writeByte(i)
    }
    bufferStatus(buffer)

    for (i in 0 until buffer.capacity()) {
        buffer.getByte(i)
    }
    bufferStatus(buffer)

    for (i in 0 until buffer.capacity()) {
        buffer.readByte()
    }
    bufferStatus(buffer)
}