package com.shengsiyuan.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

// client use telnet or nc
public class NioTest11 {

    public static void main(String[] args) throws IOException {

        // Scattering  分散  三开
        // Gathering   汇集

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.socket()
                           .bind(new InetSocketAddress(8899));

        int messageLength = 2 + 3 + 4;

        ByteBuffer[] buffers = new ByteBuffer[3];

        buffers[0] = ByteBuffer.allocate(2);
        buffers[1] = ByteBuffer.allocate(3);
        buffers[2] = ByteBuffer.allocate(4);

        SocketChannel socketChannel = serverSocketChannel.accept();

        //noinspection InfiniteLoopStatement
        while (true) {
            int bytesRead = 0;

            while (bytesRead < messageLength) {
                long r = socketChannel.read(buffers);
                bytesRead += r;
                System.out.println("bytesRead: " + bytesRead);

                Arrays.stream(buffers)
                      .map(buffer -> "position: " + buffer.position() + ", limit: " + buffer.limit())
                      .forEach(System.out::println);
            }

            Arrays.stream(buffers)
                  .forEach(Buffer::flip);

            long bytesWritten = 0;

            while (bytesWritten < messageLength) {
                long w = socketChannel.write(buffers);
                bytesWritten += w;
            }

            Arrays.stream(buffers)
                  .forEach(Buffer::clear);

            System.out.println("bytesRead: " + bytesRead +
                                       ", bytesWritten: " + bytesWritten +
                                       ", messageLength: " + messageLength);
        }
    }
}
