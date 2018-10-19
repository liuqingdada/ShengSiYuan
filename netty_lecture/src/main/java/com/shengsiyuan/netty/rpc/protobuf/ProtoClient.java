package com.shengsiyuan.netty.rpc.protobuf;

import com.shengsiyuan.protobuf.DataInfo;

import java.util.Random;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class ProtoClient {

    private static class ProtoClientHandler
            extends SimpleChannelInboundHandler<DataInfo.ProtoMessage> {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, DataInfo.ProtoMessage msg)
                throws Exception {

        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            int randomInt = new Random().nextInt(3);

            DataInfo.ProtoMessage protoMessage = null;
            switch (randomInt) {
                case 0:
                    DataInfo.Student student = DataInfo.Student.newBuilder()
                                                               .setName("zhangsan")
                                                               .setAge(18)
                                                               .setAddress("BeiJing")
                                                               .build();
                    protoMessage = DataInfo.ProtoMessage.newBuilder()
                                                        .setDataType(DataInfo.ProtoMessage
                                                                             .DataType.STUDENT_TYPE)
                                                        .setStudent(student)
                                                        .build();
                    break;

                case 1:
                    DataInfo.Dog dog = DataInfo.Dog.newBuilder()
                                                   .setName("JinMao")
                                                   .setAge(2)
                                                   .build();
                    protoMessage = DataInfo.ProtoMessage.newBuilder()
                                                        .setDataType(DataInfo.ProtoMessage
                                                                             .DataType.DOG_TYPE)
                                                        .setDog(dog)
                                                        .build();
                    break;

                case 2:
                    DataInfo.Cat cat = DataInfo.Cat.newBuilder()
                                                   .setName("MiaoMiao")
                                                   .setCity("ShangHai")
                                                   .build();
                    protoMessage = DataInfo.ProtoMessage.newBuilder()
                                                        .setDataType(DataInfo.ProtoMessage
                                                                             .DataType.CAT_TYPE)
                                                        .setCat(cat)
                                                        .build();
                    break;
            }
            if (protoMessage != null) {
                ctx.channel()
                   .writeAndFlush(protoMessage);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup)
                     .channel(NioSocketChannel.class)
                     .handler(new ChannelInitializer<SocketChannel>() {
                         @Override
                         protected void initChannel(SocketChannel ch) throws Exception {
                             ch.pipeline()
                               .addLast(new ProtobufVarint32FrameDecoder())
                               .addLast(new ProtobufDecoder(
                                       DataInfo.ProtoMessage.getDefaultInstance()))
                               .addLast(new ProtobufVarint32LengthFieldPrepender())
                               .addLast(new ProtobufEncoder())
                               .addLast(new ProtoClientHandler());
                         }
                     });

            Channel channel = bootstrap.connect("127.0.0.1", 8899)
                                       .sync()
                                       .channel()
                                       .closeFuture()
                                       .sync()
                                       .channel();

        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
