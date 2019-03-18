package com.shengsiyuan.netty.handler

import io.netty.bootstrap.Bootstrap
import io.netty.buffer.Unpooled
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInitializer
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioSocketChannel
import io.netty.handler.logging.LogLevel
import io.netty.handler.logging.LoggingHandler
import java.nio.charset.Charset

/**
 * Created by andy
 * 2019-03-18.
 * Email: 1239604859@qq.com
 */

fun main() {
    val client = Client()
    client.connect()
}

class Client {
    fun connect() {
        val workGroup = NioEventLoopGroup()
        try {
            val bootstrap = Bootstrap()
            bootstrap.group(workGroup)
                    .channel(NioSocketChannel::class.java)
                    .handler(ClientInitializer())
            val channelFuture = bootstrap.connect("127.0.0.1", 8899).sync()
            channelFuture.channel().closeFuture().sync()

        } finally {
            workGroup.shutdownGracefully()
        }
    }

    class ClientInitializer : ChannelInitializer<SocketChannel>() {
        override fun initChannel(ch: SocketChannel?) {
            ch?.pipeline()?.apply {
                addLast(LoggingHandler(LogLevel.INFO))
                addLast(ByteToLongDecoder())
                addLast(LongToByteEncoder())
                addLast(ClientHandler())
            }
        }
    }

    class ClientHandler : SimpleChannelInboundHandler<Long>() {
        override fun channelRead0(ctx: ChannelHandlerContext?, msg: Long?) {
            println("${ctx?.channel()?.remoteAddress()}, client output: $msg")
        }

        override fun channelActive(ctx: ChannelHandlerContext?) {
//            ctx?.writeAndFlush(System.currentTimeMillis())
            ctx?.writeAndFlush(Unpooled.copiedBuffer("hello world", Charset.forName("utf-8")))
        }

        @Suppress("OverridingDeprecatedMember")
        override fun exceptionCaught(ctx: ChannelHandlerContext?, cause: Throwable?) {
            cause?.printStackTrace()
            ctx?.close()
        }
    }
}