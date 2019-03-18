package com.shengsiyuan.netty.bytebuff

import io.netty.buffer.Unpooled
import java.nio.charset.Charset

/**
 * Created by andy
 * 2019-03-18.
 * Email: 1239604859@qq.com
 */

fun main() {
    println(Charset.defaultCharset())
    val buffer = Unpooled.copiedBuffer("杨柳青 hello world", Charset.forName("utf-8"))

    if (buffer.hasArray()) {
        val content = buffer.array()
        println(String(content, Charset.forName("utf-8")))

        println(buffer)
        println(buffer.arrayOffset())
        println(buffer.readerIndex())
        println(buffer.writerIndex())
        println(buffer.capacity())

        println(buffer.readableBytes())

        for (i in 0 until buffer.readableBytes()) {
            println(buffer.getByte(i).toChar())
        }

        println(buffer.getCharSequence(0, 3, Charset.forName("utf-8")))
        println(buffer.getCharSequence(3, 3, Charset.forName("utf-8")))
    }
}