package com.shengsiyuan.netty.handler

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToByteEncoder
import io.netty.handler.codec.ReplayingDecoder

/**
 * Created by andy
 * 2019-03-18.
 * Email: 1239604859@qq.com
 */

/**
 * Decoder: 解码器，收到字节数据转换成消息给业务层 bytes -> message
 */
class ByteToLongDecoder : ReplayingDecoder<Unit>() {
    override fun decode(ctx: ChannelHandlerContext?, inBuffer: ByteBuf?, out: MutableList<Any>?) {
        println("ByteToLongDecoder is invoked")
        inBuffer?.readLong()?.run {
            out?.add(this)
        }
    }
}

class LongToByteEncoder : MessageToByteEncoder<Long>() {
    override fun encode(ctx: ChannelHandlerContext?, msg: Long?, out: ByteBuf?) {
        println("LongToByteEncoder is invoked")
        println(msg)
        msg?.run {
            out?.writeLong(this)
        }
    }
}
