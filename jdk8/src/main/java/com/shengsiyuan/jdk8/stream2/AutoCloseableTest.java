package com.shengsiyuan.jdk8.stream2;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class AutoCloseableTest implements AutoCloseable {

    public static void main(String[] args) {
        AutoCloseableTest act = null;
        try {
            act = new AutoCloseableTest();
            act.doSomething();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (act != null) {
                    act.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("old finally");
        }

        System.out.println("=====================");

        try (AutoCloseableTest autoCloseableTest = new AutoCloseableTest()) {
            autoCloseableTest.doSomething();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            System.out.println("new finally");
        }

        System.out.println("===============");

        try (InputStream is = new IS("/Users/liuqing/KeepLive.java");
             BufferedInputStream bis = new BIS(is)) {

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("io stream finally");
        }
    }

    private void doSomething() {
        System.out.println("do some ... ...");
    }

    @Override
    public void close() throws Exception {
        System.out.println("close invocked");
    }

    private static class IS extends FileInputStream {
        public IS(String name) throws FileNotFoundException {
            super(name);
        }

        @Override
        public void close() throws IOException {
            super.close();
            System.out.println("fis close");
        }
    }

    private static class BIS extends BufferedInputStream {
        public BIS(InputStream in) {
            super(in);
        }

        @Override
        public void close() throws IOException {
            super.close();
            System.out.println("bis close");
        }
    }
}
