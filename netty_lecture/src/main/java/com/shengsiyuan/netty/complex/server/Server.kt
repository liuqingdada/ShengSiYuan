package com.shengsiyuan.netty.complex.server

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelInitializer
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.handler.codec.LengthFieldBasedFrameDecoder
import io.netty.handler.codec.LengthFieldPrepender
import io.netty.handler.codec.string.StringDecoder
import io.netty.handler.codec.string.StringEncoder
import io.netty.handler.logging.LogLevel
import io.netty.handler.logging.LoggingHandler
import io.netty.util.CharsetUtil

/**
 * Created by cooper
 * 2020/6/23.
 * Email: 1239604859@qq.com
 */
class Server {
    fun run() {
        val bossGroup = NioEventLoopGroup(1)
        val workerGroup = NioEventLoopGroup()
        try {
            val serverBootstrap = ServerBootstrap()
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel::class.java)
                    .handler(LoggingHandler(LogLevel.INFO))
                    .childHandler(Childhandler())
            val bindFuture = serverBootstrap.bind(8899).sync()
            bindFuture.channel().closeFuture().sync()
        } finally {
            bossGroup.shutdownGracefully()
            workerGroup.shutdownGracefully()
       }
    }

    private class Childhandler : ChannelInitializer<SocketChannel>() {
        override fun initChannel(ch: SocketChannel?) {
            ch?.pipeline()?.apply {
                addLast(LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4))
                addLast(LengthFieldPrepender(4))
                addLast(StringDecoder(CharsetUtil.UTF_8))
                addLast(StringEncoder(CharsetUtil.UTF_8))
            }
        }
    }
}

fun main() {

}