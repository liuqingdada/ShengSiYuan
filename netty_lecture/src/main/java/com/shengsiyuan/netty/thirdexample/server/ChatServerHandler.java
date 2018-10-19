package com.shengsiyuan.netty.thirdexample.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class ChatServerHandler extends SimpleChannelInboundHandler<String> {
    private static ChannelGroup sChannels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        sChannels.stream()
                 .filter(ch -> ch != channel)
                 .forEach(ch -> ch.writeAndFlush(channel.remoteAddress() + "发送了消息: " + msg + "\n"));
    }

    /**
     * 客户端上线
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();

        sChannels.writeAndFlush("[server] -> " + channel.remoteAddress() + ", ADDED\n");

        sChannels.add(channel);
    }

    /**
     * 客户端下线
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();

        //sChannels.remove(channel); // GlobalEventExecutor 会自动移除出去, 不写也可以
        sChannels.writeAndFlush("[server] -> " + channel.remoteAddress() + ", REMOVED\n");
        System.out.println(sChannels.size());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + " is online");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + " is offline");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
