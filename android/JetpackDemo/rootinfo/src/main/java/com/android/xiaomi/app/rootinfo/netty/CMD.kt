package com.android.xiaomi.app.rootinfo.netty

import io.netty.channel.ChannelHandlerContext

/**
 * Created by cooper
 * 20-6-19.
 * Email: 1239604859@qq.com
 */
object CMD {
    const val HEARTBEAT_PIGN = 1L
    const val HEARTBEAT_PONG = 2L

    const val GET_LOG_FILE = 3L
}

object CmdHandler {
    fun handle(ctx: ChannelHandlerContext, msg: RcMessage) {
        when (msg.cmd) {
            CMD.GET_LOG_FILE -> {
            }
        }
    }
}