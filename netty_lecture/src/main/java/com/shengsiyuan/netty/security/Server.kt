package com.shengsiyuan.netty.security

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInitializer
import io.netty.channel.EventLoopGroup
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.channel.group.ChannelGroup
import io.netty.channel.group.DefaultChannelGroup
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.handler.codec.DelimiterBasedFrameDecoder
import io.netty.handler.codec.Delimiters
import io.netty.handler.codec.string.StringDecoder
import io.netty.handler.codec.string.StringEncoder
import io.netty.handler.logging.LogLevel
import io.netty.handler.logging.LoggingHandler
import io.netty.handler.ssl.SslContext
import io.netty.handler.ssl.SslContextBuilder
import io.netty.handler.ssl.SslHandler
import io.netty.util.concurrent.GlobalEventExecutor
import java.io.File
import java.net.InetAddress

/**
 * Created by cooper
 * 2020/12/10.
 * Email: 1239604859@qq.com
 *
 * Download KeyStore Explore GUI:
 * http://keystore-explorer.org/
 */
class Server {
    companion object {
        const val PORT = 8993
    }

    fun run() {
        val sslContext = SslContextBuilder.forServer(
            File("/home/cooper/suhen/kse_x509.cer"),
            File("/home/cooper/suhen/kse_pkcs8.key")
        ).build()

        val bossGroup: EventLoopGroup = NioEventLoopGroup(1)
        val workerGroup: EventLoopGroup = NioEventLoopGroup()
        try {
            val b = ServerBootstrap()
            b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel::class.java)
                .handler(LoggingHandler(LogLevel.INFO))
                .childHandler(ChildHandler(sslContext))
            b.bind(PORT).sync().channel().closeFuture().sync()
        } finally {
            bossGroup.shutdownGracefully()
            workerGroup.shutdownGracefully()
        }
    }
}

class ChildHandler(private val sslContext: SslContext) : ChannelInitializer<SocketChannel>() {
    override fun initChannel(ch: SocketChannel?) {
        ch?.pipeline()?.apply {
            addLast(sslContext.newHandler(ch.alloc()))
            addLast(DelimiterBasedFrameDecoder(8192, *Delimiters.lineDelimiter()))
            addLast(StringDecoder())
            addLast(StringEncoder())
            addLast(LogicServerHandler())
        }
    }
}

class LogicServerHandler : SimpleChannelInboundHandler<String>() {
    companion object {
        val channels: ChannelGroup = DefaultChannelGroup(GlobalEventExecutor.INSTANCE)
    }

    override fun channelActive(ctx: ChannelHandlerContext) {
        // Once session is secured, send a greeting and register the channel to the global channel
        // list so the channel received the messages from others.
        ctx.pipeline().get(SslHandler::class.java).handshakeFuture().addListener {
            ctx.writeAndFlush(
                """Welcome to ${InetAddress.getLocalHost().hostName} secure chat service!"""
            )
            ctx.writeAndFlush(
                "Your session is protected by " +
                        ctx.pipeline().get(SslHandler::class.java)
                            .engine().session.cipherSuite +
                        " cipher suite.\n"
            )
            channels.add(ctx.channel())
        }
    }

    @Throws(Exception::class)
    override fun channelRead0(ctx: ChannelHandlerContext, msg: String) {
        // Send the received message to all channels but the current one.
        for (c in channels) {
            if (c !== ctx.channel()) {
                c.writeAndFlush(
                    """
                    [${ctx.channel().remoteAddress()}] $msg
                    
                    """.trimIndent()
                )
            } else {
                c.writeAndFlush("[you] $msg\n")
            }
        }

        // Close the connection if the client has sent 'bye'.
        if ("bye" == msg.toLowerCase()) {
            ctx.close()
        }
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {
        cause.printStackTrace()
        ctx.close()
    }
}

fun main() {
    Server().run()
}
