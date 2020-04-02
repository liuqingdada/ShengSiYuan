package com.shengsiyuan;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by liuqing.yang
 * 2020/4/2.
 * Email: 1239604859@qq.com
 */
public class FileUtil {
    public static void splitFile(String filePath, int fileCount) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        FileChannel inputChannel = fis.getChannel();
        final long fileSize = inputChannel.size();
        long average = fileSize / fileCount; // 平均值
        long bufferSize = 200; // 缓存块大小，自行调整
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) bufferSize); // 申请一个缓存区
        long startPosition = 0; // 子文件开始位置
        long endPosition = average < bufferSize ? 0 : average - bufferSize; //子文件结束位置
        for (int i = 0; i < fileCount; i++) {
            if (i + 1 != fileCount) {
                int read = inputChannel.read(byteBuffer, endPosition); // 读取数据
                readW:
                while (read != -1) {
                    byteBuffer.flip(); // 切换读模式
                    byte[] array = byteBuffer.array();
                    for (int j = 0; j < array.length; j++) {
                        byte b = array[j];
                        if (b == 10 || b == 13) { // 判断\n\r
                            endPosition += j;
                            break readW;
                        }
                    }
                    endPosition += bufferSize;
                    byteBuffer.clear(); // 重置缓存块指针
                    read = inputChannel.read(byteBuffer, endPosition);
                }
            } else {
                endPosition = fileSize; // 最后一个文件直接指向文件末尾
            }

            FileOutputStream fos = new FileOutputStream(filePath + (i + 1));
            FileChannel outputChannel = fos.getChannel();
            inputChannel.transferTo(startPosition, endPosition - startPosition, outputChannel); // 通道传输文件数据
            outputChannel.close();
            fos.close();
            startPosition = endPosition + 1;
            endPosition += average;
        }
        inputChannel.close();
        fis.close();
    }
}
