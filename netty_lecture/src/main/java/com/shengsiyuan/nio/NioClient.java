package com.shengsiyuan.nio;

import com.java.lang.UncheckedRunnable;
import com.java.util.function.UncheckedConsumer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NioClient {

    @SuppressWarnings("InfiniteLoopStatement")
    private void connect() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8899));

        while (true) {
            selector.select();

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            selectionKeys.forEach(UncheckedConsumer.unchecked(selectionKey -> {
                if (selectionKey.isConnectable()) {
                    SocketChannel client = (SocketChannel) selectionKey.channel();

                    if (client.isConnectionPending()) {
                        client.finishConnect();

                        ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                        writeBuffer.put((LocalDateTime.now() + ", new client online\n").getBytes());
                        writeBuffer.flip();
                        client.write(writeBuffer);

                        ExecutorService inputService = Executors.newSingleThreadExecutor();
                        inputService.execute(UncheckedRunnable.unchecked(() -> {
                            while (true) {
                                writeBuffer.clear();

                                InputStreamReader isr = new InputStreamReader(System.in);
                                BufferedReader br = new BufferedReader(isr);
                                writeBuffer.put((br.readLine() + "\n").getBytes());
                                writeBuffer.flip();
                                client.write(writeBuffer);
                            }
                        }));
                    }

                    client.register(selector, SelectionKey.OP_READ);

                } else if (selectionKey.isReadable()) {
                    SocketChannel client = (SocketChannel) selectionKey.channel();

                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                    int read = client.read(readBuffer);

                    if (read > 0) {
                        String msg = new String(readBuffer.array(), 0, read);
                        System.out.print(msg);
                    }
                }
            }));

            selectionKeys.clear();
        }
    }

    public static void main(String[] args) throws IOException {
        new NioClient().connect();
//        for (int i = 0; i < 100; i++) {
//            new Thread(() -> {
//                try {
//                    new NioClient().connect();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }).start();
//        }
    }
}
