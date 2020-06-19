package com.android.xiaomi.app.rootinfo.netty

import android.util.Log
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter
import io.netty.handler.codec.ByteToMessageDecoder
import io.netty.handler.codec.MessageToByteEncoder
import io.netty.handler.timeout.IdleState
import io.netty.handler.timeout.IdleStateEvent

/**
 * Created by cooper
 * 20-6-18.
 * Email: 1239604859@qq.com
 */

interface HeartbeatCallback {
    fun onServerClose()
}

interface ClientCallback {
    fun onDisconnect()
}

class HeartbeatClientHandler(private val heartbeatCallback: HeartbeatCallback) :
    ChannelInboundHandlerAdapter() {
    companion object {
        private const val TAG = "HeartbeatClientHandler"
    }

    override fun userEventTriggered(ctx: ChannelHandlerContext?, evt: Any?) {
        if (evt is IdleStateEvent) {
            var event = ""
            evt.state()?.apply {
                event = when (this) {
                    IdleState.READER_IDLE -> {
                        heartbeatCallback.onServerClose()
                        "read idle"
                    }
                    IdleState.WRITER_IDLE -> {
                        ctx?.writeAndFlush(RcMessage(CMD.HEARTBEAT_PIGN))
                        "write idle"
                    }
                    IdleState.ALL_IDLE -> "read write all adle"
                }
            }
            Log.d(TAG, "client: ${ctx?.channel()?.localAddress()}: $event")
        }
    }
}

class HeartbeatServerHandler : ChannelInboundHandlerAdapter() {
    companion object {
        private const val TAG = "HeartbeatServerHandler"
    }

    override fun userEventTriggered(ctx: ChannelHandlerContext?, evt: Any?) {
        if (evt is IdleStateEvent) {
            evt.state()?.apply {
                when (this) {
                    IdleState.ALL_IDLE -> {
                        ctx?.channel()?.close()
                        Log.d(
                            TAG,
                            "close client: ${ctx?.channel()?.remoteAddress()}: read write idle"
                        )
                    }
                    else -> {
                    }
                }
            }
        }
    }
}

data class RcMessage(val cmd: Long)

class RcMessageToByteEncoder : MessageToByteEncoder<RcMessage>() {
    override fun encode(ctx: ChannelHandlerContext?, msg: RcMessage?, out: ByteBuf?) {
        msg?.apply {
            out?.apply {
                writeLong(msg.cmd)
            }
        }
    }
}

class ByteToRcMessageDecoder : ByteToMessageDecoder() {
    override fun decode(ctx: ChannelHandlerContext?, inBuff: ByteBuf?, out: MutableList<Any>?) {
        inBuff?.apply {
            out?.apply {
                val cmd = inBuff.readLong()

                out.add(RcMessage(cmd))
            }
        }
    }
}