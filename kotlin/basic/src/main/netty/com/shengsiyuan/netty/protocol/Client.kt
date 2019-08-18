package com.shengsiyuan.netty.protocol

import io.netty.bootstrap.Bootstrap
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInitializer
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioSocketChannel
import io.netty.util.NettyRuntime
import java.util.*


/**
 * Created by andy
 * 2019-03-18.
 * Email: 1239604859@qq.com
 */

fun main() {
    for (i in 0 until 10) {
        Thread {
            Client().connect()
        }.start()
        Thread.sleep(100)
    }
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

    private class ClientInitializer : ChannelInitializer<SocketChannel>() {
        override fun initChannel(ch: SocketChannel?) {
            ch?.pipeline()?.apply {
                addLast(ProtocolMessageDecoder())
                addLast(ProtocolMessageEncoder())
                addLast(ClientHandler())
            }
        }
    }

    private class ClientHandler : SimpleChannelInboundHandler<ProtocolMessage>() {
        private val service = getBlockingService(2, NettyRuntime.availableProcessors() * 4)

        override fun channelActive(ctx: ChannelHandlerContext?) {
            service.execute {
                val json = "json or proto or gzip file data".toByteArray()
                while (true) {
                    val data = ProtocolMessage(json.size, json)
                    ctx?.writeAndFlush(data)

                    Thread.sleep(1000)
                }
            }
        }

        override fun channelRead0(ctx: ChannelHandlerContext?, msg: ProtocolMessage?) {
            service.execute {
                ctx?.apply {
                    msg?.apply {
                        println("${ctx.channel()?.remoteAddress()}: ${msg.length} -> ${Arrays.toString(msg.content)}")
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