package com.shengsiyuan.netty.security

import io.netty.bootstrap.Bootstrap
import io.netty.channel.ChannelFuture
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInitializer
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioSocketChannel
import io.netty.handler.codec.DelimiterBasedFrameDecoder
import io.netty.handler.codec.Delimiters
import io.netty.handler.codec.string.StringDecoder
import io.netty.handler.codec.string.StringEncoder
import io.netty.handler.ssl.SslContext
import io.netty.handler.ssl.SslContextBuilder
import java.io.File

/**
 * Created by cooper
 * 2020/12/10.
 * Email: 1239604859@qq.com
 */
class Client {
    companion object {
        const val HOST = "127.0.0.1"
        const val PORT = 8993
    }

    fun connect() {
        val sslContext = SslContextBuilder.forClient()
            .trustManager(File("/home/cooper/suhen/kse_x509.cer"))
            .build()
        val group = NioEventLoopGroup();
        try {
            val b = Bootstrap()
            b.group(group)
                .channel(NioSocketChannel::class.java)
                .handler(ClientHandler(sslContext))
            val channel = b.connect(HOST, PORT).sync().channel()

            var lastWrite: ChannelFuture? = null
            System.`in`.bufferedReader().use {
                while (true) {
                    val line = it.readLine() ?: break
                    lastWrite = channel.writeAndFlush(line + "\r\n")

                    if ("bye" == line.toLowerCase()) {
                        channel.closeFuture().sync()
                        break
                    }
                }
            }
            lastWrite?.sync()
        } finally {
            group.shutdownGracefully()
        }
    }
}

class ClientHandler(private val sslContext: SslContext) : ChannelInitializer<SocketChannel>() {
    override fun initChannel(ch: SocketChannel?) {
        ch?.pipeline()?.apply {
            addLast(sslContext.newHandler(ch.alloc(), Client.HOST, Client.PORT))
            addLast(DelimiterBasedFrameDecoder(8192, *Delimiters.lineDelimiter()))
            addLast(StringDecoder())
            addLast(StringEncoder())
            addLast(LogicClientHandler())
        }
    }
}

class LogicClientHandler : SimpleChannelInboundHandler<String>() {
    override fun channelRead0(ctx: ChannelHandlerContext?, msg: String?) {
        println(msg)
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext?, cause: Throwable?) {
        cause?.printStackTrace()
        ctx?.close()
    }
}

fun main() {
    Client().connect()
}
