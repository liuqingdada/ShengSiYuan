package com.android.xiaomi.app.rootinfo.netty

import android.util.Log
import com.android.app.common.utils.LogUtil
import com.android.app.common.utils.execute
import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.*
import io.netty.channel.group.DefaultChannelGroup
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.handler.codec.LengthFieldBasedFrameDecoder
import io.netty.handler.codec.LengthFieldPrepender
import io.netty.handler.codec.serialization.ObjectDecoder
import io.netty.handler.codec.serialization.ObjectEncoder
import io.netty.handler.logging.LogLevel
import io.netty.handler.logging.LoggingHandler
import io.netty.handler.timeout.IdleStateHandler
import io.netty.util.concurrent.GlobalEventExecutor
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

/**
 * Created by cooper
 * 20-6-18.
 * Email: 1239604859@qq.com
 */
class NettyServer {
    companion object {
        private const val TAG = "NettyServer"
        private const val READ_IDLE_TIME: Long = 0
        private const val WRITE_IDLE_TIME: Long = 0
        private const val READ_WRITE_IDLE_TIME: Long = 20

        val channels = DefaultChannelGroup(GlobalEventExecutor.INSTANCE)
    }

    private val bindThread = Executors.newSingleThreadScheduledExecutor()

    private var bossGroup: NioEventLoopGroup? = null
    private var workGroup: NioEventLoopGroup? = null
    private var channel: Channel? = null

    fun run(port: Int) {
        if (channel?.isActive == true) {
            Log.v(TAG, "channel is active")
            return
        }

        if (bossGroup != null) {
            Log.v(TAG, "nio loop group is already init")
            return
        }

        try {
            bossGroup = NioEventLoopGroup(1)
            workGroup = NioEventLoopGroup()

            val serverBootstrap = ServerBootstrap()
            serverBootstrap.group(bossGroup, workGroup)
                .handler(LoggingHandler(LogLevel.INFO))
                .channel(NioServerSocketChannel::class.java)
                .childHandler(ServerInitializer())
            val channelFuture = serverBootstrap.bind(port).sync()
            channelFuture.addListener(object : ChannelFutureListener {
                override fun operationComplete(future: ChannelFuture?) {
                    if (future?.isSuccess == true) {
                        channel = future.channel()
                        Log.i(TAG, "order server is bind success")
                    } else {
                        Log.i(TAG, "order server is bind failed, try rebind after 10 second")
                        future?.channel()?.eventLoop()?.schedule({
                            run(port)
                        }, 10, TimeUnit.SECONDS)
                    }
                }
            })

            channel?.closeFuture()?.sync()
        } catch (e: Exception) {
            Log.e(TAG, "run: ", e)
            close()
            bindThread.schedule({
                run(port)
            }, 10, TimeUnit.SECONDS)
        }
    }

    fun close() {
        bossGroup?.shutdownGracefully()
        workGroup?.shutdownGracefully()
        bossGroup = null
        workGroup = null
        channel = null
    }

    fun stop() {
        close()
        bindThread.shutdownNow()
    }

    private class ServerInitializer : ChannelInitializer<SocketChannel>() {
        override fun initChannel(ch: SocketChannel?) {
            ch?.pipeline()?.apply {
                addLast(LengthFieldBasedFrameDecoder(Int.MAX_VALUE, 0, 4, 0, 4))
                addLast(LengthFieldPrepender(4))
                addLast(
                    IdleStateHandler(
                        READ_IDLE_TIME,
                        WRITE_IDLE_TIME,
                        READ_WRITE_IDLE_TIME,
                        TimeUnit.SECONDS
                    )
                )
                addLast(RcMessageToByteEncoder())
                addLast(ByteToRcMessageDecoder())
                addLast(HeartbeatServerHandler())
                addLast(NettyServerHandler())
            }
        }
    }

    private class NettyServerHandler : SimpleChannelInboundHandler<RcMessage>() {
        override fun channelRead0(ctx: ChannelHandlerContext?, msg: RcMessage?) {
            execute {
                ctx?.apply {
                    msg?.apply {
                        if (msg.cmd == CMD.HEARTBEAT_PIGN) {
                            Log.d(TAG, "channelRead0: PING")
                            ctx.writeAndFlush(RcMessage(CMD.HEARTBEAT_PONG))
                        } else {
                            CmdHandler.handle(ctx, msg)
                        }
                    }
                }
            }
        }

        override fun channelActive(ctx: ChannelHandlerContext?) {
            channels.add(ctx?.channel())
            LogUtil.d(TAG, "client: ${ctx?.channel()?.remoteAddress()} is connected")
        }

        override fun channelInactive(ctx: ChannelHandlerContext?) {
            channels.remove(ctx?.channel())
            LogUtil.d(TAG, "client: ${ctx?.channel()?.remoteAddress()} is disconnected")
        }

        override fun exceptionCaught(ctx: ChannelHandlerContext?, cause: Throwable?) {
            cause?.printStackTrace()
            ctx?.close()
        }
    }
}