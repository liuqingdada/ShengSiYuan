package com.shengsiyuan.zerocopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NewIOServer {

    private void run() throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.setReuseAddress(true);
        serverSocket.bind(new InetSocketAddress(8899));

        ByteBuffer buffer = ByteBuffer.allocate(4096);

        //noinspection InfiniteLoopStatement
        while (true) {
            SocketChannel client = serverSocketChannel.accept();
            client.configureBlocking(true);

            int read = 0;

            while (-1 != read) {
                read = client.read(buffer);

                buffer.rewind();
            }
        }
    }

    public static void main(String[] args) {
        try {
            new NewIOServer().run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
