package com.shengsiyuan.zerocopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.time.Duration;
import java.time.Instant;

public class OldClient {

    private void connect() throws IOException {
        Socket socket = new Socket("127.0.0.1", 8899);

        String bigFilePath = "/Users/liuqing/work/subor/VRTeacher.tar.gz"; // 300MB
        InputStream is = new FileInputStream(bigFilePath);

        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        byte[] buffer = new byte[4096];
        long readCount = 0;
        long total = 0;

        Instant start = Instant.now();
        while ((readCount = is.read(buffer)) >= 0) {
            total += readCount;

            dos.write(buffer);
        }
        Instant end = Instant.now();

        System.out.println("total bytes: " + total + ", take time: " + Duration.between(start, end)
                                                                               .toMillis());
        dos.close();
        socket.close();
        is.close();
    }

    public static void main(String[] args) {
        try {
            new OldClient().connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
