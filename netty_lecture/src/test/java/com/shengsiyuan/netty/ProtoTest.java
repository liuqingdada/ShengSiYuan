package com.shengsiyuan.netty;

import com.google.protobuf.InvalidProtocolBufferException;
import com.shengsiyuan.protobuf.DataInfo;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class ProtoTest {

    @Test
    public void testDataInfo() throws InvalidProtocolBufferException {
        DataInfo.Student student = DataInfo.Student.newBuilder()
                                                   .setName("zhangsan")
                                                   .setAge(18)
                                                   .setAddress("BeiJing")
                                                   .build();
        byte[] studentBytes = student.toByteArray();
        System.out.println(Arrays.toString(studentBytes));


        DataInfo.Student student2 = DataInfo.Student.parseFrom(studentBytes);
        System.out.println(student2);
    }

    @Test
    public void thread() {
        AtomicInteger ai = new AtomicInteger(0);

        while (true) {
            new Thread(() -> {
                System.out.println(ai.addAndGet(1) + ", " + Thread.currentThread()
                                                                  .getName());
                synchronized (this) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }).start();
        }
    }
}
