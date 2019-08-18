package com.shengsiyuan.netty.handler

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInitializer
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.handler.codec.MessageToMessageDecoder
import io.netty.handler.logging.LogLevel
import io.netty.handler.logging.LoggingHandler

/**
 * Created by andy
 * 2019-03-18.
 * Email: 1239604859@qq.com
 */

fun main() {
    val server = Server()
    server.run()
}

class Server {
    fun run() {
        val bossGroup = NioEventLoopGroup(1)
        val workGroup = NioEventLoopGroup()

        try {
            val serverBootstrap = ServerBootstrap()
            serverBootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel::class.java)
                    .handler(LoggingHandler(LogLevel.INFO))
                    .childHandler(ServerInitializer())

            val channelFuture = serverBootstrap.bind(8899).sync()
            channelFuture.channel().closeFuture().sync()

        } finally {
            bossGroup.shutdownGracefully()
            workGroup.shutdownGracefully()
        }
    }

    private class ServerInitializer : ChannelInitializer<SocketChannel>() {
        override fun initChannel(ch: SocketChannel?) {
            ch?.pipeline()?.apply {
                addLast(ByteToLongDecoder())
                addLast(LongToStringDecoder())
                addLast(LongToByteEncoder())
                addLast(ServerHandler())
            }
        }
    }

    private class LongToStringDecoder : MessageToMessageDecoder<Long>() {
        override fun decode(ctx: ChannelHandlerContext?, msg: Long?, out: MutableList<Any>?) {
            println("LongToStringDecoder is invoked")
            msg?.toString()?.run {
                out?.add(this)
            }
        }
    }

    private class ServerHandler : SimpleChannelInboundHandler<String>() {
        override fun channelRead0(ctx: ChannelHandlerContext?, msg: String?) {
            println("${ctx?.channel()?.remoteAddress()}, $msg")
            ctx?.writeAndFlush(System.currentTimeMillis())
        }

        @Suppress("OverridingDeprecatedMember")
        override fun exceptionCaught(ctx: ChannelHandlerContext?, cause: Throwable?) {
            cause?.printStackTrace()
            ctx?.close()
        }
    }
}