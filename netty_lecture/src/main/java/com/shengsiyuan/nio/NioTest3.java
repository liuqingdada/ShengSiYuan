package com.shengsiyuan.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest3 {

    public static void main(String[] args) throws Exception {
        ByteBuffer byteBuffer = ByteBuffer.allocate(8 * 1024);

        FileOutputStream fos = new FileOutputStream("test_file/NioTest3.txt");
        FileChannel writeChannel = fos.getChannel();

        byte[] bytes = "hello world".getBytes();

        for (byte b : bytes) {
            byteBuffer.put(b);
        }

        byteBuffer.flip();

        writeChannel.write(byteBuffer);

        writeChannel.close();
        fos.close();

        ////////////////////////////////
        ////////////////////////////////

        FileInputStream fis = new FileInputStream("test_file/NioTest3.txt");
        FileChannel readChannel = fis.getChannel();

        byteBuffer.flip();
        readChannel.read(byteBuffer);

        byteBuffer.flip();

        while (byteBuffer.hasRemaining()) {
            System.out.println((char) byteBuffer.get());
        }

        readChannel.close();
        fis.close();
    }
}
