package com.shengsiyuan.netty.protocol

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToByteEncoder
import io.netty.handler.codec.ReplayingDecoder
import java.util.concurrent.ExecutorService
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/**
 * Created by andy
 * 2019-03-18.
 * Email: 1239604859@qq.com
 */

class ProtocolMessageEncoder : MessageToByteEncoder<ProtocolMessage>() {
    override fun encode(ctx: ChannelHandlerContext?, msg: ProtocolMessage?, out: ByteBuf?) {
        msg?.apply {
            out?.apply {
                writeInt(msg.length)
                writeBytes(msg.content)
            }
        }
    }
}

class ProtocolMessageDecoder : ReplayingDecoder<Unit>() {
    override fun decode(ctx: ChannelHandlerContext?, inBuff: ByteBuf?, out: MutableList<Any>?) {
        inBuff?.apply {
            val length = inBuff.readInt()
            val content = ByteArray(length)
            inBuff.readBytes(content)

            out?.apply {
                add(ProtocolMessage(length, content))
            }
        }
    }
}

data class ProtocolMessage(val length: Int,
                           val content: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ProtocolMessage

        if (length != other.length) return false
        if (!content.contentEquals(other.content)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = length
        result = 31 * result + content.contentHashCode()
        return result
    }
}

fun getBlockingService(corePoolSize: Int, capacity: Int): ExecutorService {
    return ThreadPoolExecutor(corePoolSize, corePoolSize, 0L, TimeUnit.SECONDS,
            LinkedBlockingQueue(capacity)) { r, exec ->
        try {
            if (!exec.isShutdown) {
                exec.queue.put(r)
            }
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}