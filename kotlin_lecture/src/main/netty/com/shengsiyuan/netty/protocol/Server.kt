package com.shengsiyuan.netty.protocol

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInitializer
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.handler.logging.LogLevel
import io.netty.handler.logging.LoggingHandler
import io.netty.util.NettyRuntime
import java.util.concurrent.ExecutorService

/**
 * Created by andy
 * 2019-03-18.
 * Email: 1239604859@qq.com
 */

fun main() {
    Server().run()
}

class Server {
    private val service = getBlockingService(1, NettyRuntime.availableProcessors() * 16)

    fun run() {
        val bossGroup = NioEventLoopGroup(1)
        val workGroup = NioEventLoopGroup()

        try {
            val serverBootstrap = ServerBootstrap()
            serverBootstrap.group(bossGroup, workGroup)
                    .handler(LoggingHandler(LogLevel.INFO))
                    .channel(NioServerSocketChannel::class.java)
                    .childHandler(ServerInitializer(service))
            val channelFuture = serverBootstrap.bind(8899).sync()
            channelFuture.channel().closeFuture().sync()
        } finally {
            bossGroup.shutdownGracefully()
            workGroup.shutdownGracefully()
        }
    }

    private class ServerInitializer(val service: ExecutorService) : ChannelInitializer<SocketChannel>() {
        override fun initChannel(ch: SocketChannel?) {
            ch?.pipeline()?.apply {
                addLast(ProtocolMessageDecoder())
                addLast(ProtocolMessageEncoder())
                addLast(ServiceHandler(service))
            }
        }
    }

    private class ServiceHandler(val service: ExecutorService) : SimpleChannelInboundHandler<ProtocolMessage>() {
        override fun channelRead0(ctx: ChannelHandlerContext?, msg: ProtocolMessage?) {
            service.execute {
                ctx?.apply {
                    msg?.apply {
                        println("${ctx.channel()?.remoteAddress()}: ${msg.length} -> ${String(msg.content)}")
                        val data = ProtocolMessage(16, byteArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15))
                        ctx.writeAndFlush(data)
                    }
                }
            }
        }

        override fun exceptionCaught(ctx: ChannelHandlerContext?, cause: Throwable?) {
            cause?.printStackTrace()
            ctx?.close()
        }
    }
}
