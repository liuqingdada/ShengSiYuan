package com.shengsiyuan.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest9 {

    public static void main(String[] args) throws Exception {
        RandomAccessFile raf = new RandomAccessFile("test_file/NioTest9.txt", "rw");

        FileChannel fileChannel = raf.getChannel();

        MappedByteBuffer mapper = fileChannel.map(FileChannel.MapMode.READ_WRITE,
                                                  0,
                                                  1024);
        mapper.put("hello world".getBytes());

        fileChannel.close();
        raf.close();

    }
}
