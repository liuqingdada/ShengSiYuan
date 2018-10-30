package com.shengsiyuan.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest2 {

    public static void main(String[] args) throws IOException {

        FileInputStream fis = new FileInputStream("test_file/NioTest2.txt");

        FileChannel fileChannel = fis.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(8 * 1024);

        fileChannel.read(byteBuffer); // write memory
        byteBuffer.flip();

        while (byteBuffer.remaining() > 0) {
            byte b = byteBuffer.get();
            System.out.println((char) b);
        }

        fileChannel.close();
        fis.close();
    }
}
