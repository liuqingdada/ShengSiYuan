package com.shengsiyuan.netty.bytebuff

import io.netty.buffer.Unpooled

/**
 * Created by andy
 * 2019-03-18.
 * Email: 1239604859@qq.com
 */

fun main() {
    val compositeBuffer = Unpooled.compositeBuffer()
    val heapBuff = Unpooled.buffer(10)
    val nativeBuff = Unpooled.directBuffer(10)

    compositeBuffer.addComponents(heapBuff, nativeBuff)
//    compositeBuffer.removeComponent(0)
    println(compositeBuffer)

    compositeBuffer.forEach { println(it) }
}