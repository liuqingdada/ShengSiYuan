package com.shengsiyuan.netty.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadTest {
    private int availableProcessors = Runtime.getRuntime()
                                             .availableProcessors();
    private int maximumPoolSize = availableProcessors * 2 + 2;

    private ThreadTest() {
        System.out.println(availableProcessors);
        System.out.println("==========================");
    }

    private ExecutorService system() {
        return Executors.newFixedThreadPool(availableProcessors);
    }

    private ExecutorService blocking() {
        return new ThreadPoolExecutor(
                availableProcessors, maximumPoolSize, 60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(maximumPoolSize),
                (r, exe) -> {
                    try {
                        if (!exe.isShutdown()) {
                            exe.getQueue()
                               .put(r);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
    }

    private void consume(ExecutorService service) {
        for (long i = 0; i < 1000000000000L; i++) {
            service.execute(() -> {
                synchronized (this) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("never finished this task");
            });
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        ThreadTest test = new ThreadTest();
        //        test.consume(test.system());
        test.consume(test.blocking());
    }
}
