package com.shengsiyuan.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class NioTest13 {

    public static void main(String[] args) throws IOException {

        String inFile = "test_file/NioTest13_in.txt";
        String outFile = "test_file/NioTest13_out.txt";

        RandomAccessFile rafIn = new RandomAccessFile(inFile, "r");
        RandomAccessFile rafOut = new RandomAccessFile(outFile, "rw");

        FileChannel rafInChannel = rafIn.getChannel();
        FileChannel rafOutChannel = rafOut.getChannel();

        MappedByteBuffer mappedByteBuffer = rafInChannel.map(FileChannel.MapMode.READ_ONLY,
                                                             0,
                                                             rafIn.length());
        Charset charset = Charset.forName("utf-8");
        CharsetDecoder charsetDecoder = charset.newDecoder();
        CharsetEncoder charsetEncoder = charset.newEncoder();

        CharBuffer charBuffer = charsetDecoder.decode(mappedByteBuffer);
        ByteBuffer byteBuffer = charsetEncoder.encode(charBuffer);

        rafOutChannel.write(byteBuffer);

        rafIn.close();
        rafOut.close();
    }
}
