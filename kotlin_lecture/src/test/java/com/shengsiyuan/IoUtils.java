package com.shengsiyuan;

import java.io.Closeable;

public class IoUtils {
    /**
     * Close the closeable target and eat possible exceptions.
     *
     * @param target The target to close. Can be null.
     */
    public static void closeQuietly(Closeable target) {
        try {
            if (target != null) {
                target.close();
            }
        } catch (Exception e) {
            // ignore
        }
    }

    public static String bytes2Hex(byte[] bytes) {
        StringBuilder buf = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) { // 使用String的format方法进行转换
            buf.append(String.format("%02X", b & 0xff));
        }

        return buf.toString();
    }

    public static byte[] hexStr2Bytes(String hexStr) {
        if (hexStr == null || hexStr.trim().equals("")) {
            return new byte[0];
        }

        byte[] bytes = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            String subStr = hexStr.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }

        return bytes;
    }
}
