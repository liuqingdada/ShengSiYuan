package com.shengsiyuan;

import org.junit.Test;

import java.util.BitSet;

/**
 * Created by andy
 * 2019-04-15.
 * Email: 1239604859@qq.com
 */

public class ByteTest {

    private byte[] byteToBitArray(byte b) {
        byte[] array = new byte[8];
        for (int i = 7; i >= 0; i--) {
            array[i] = (byte) (b & 1);
            b = (byte) (b >> 1);
        }
        return array;
    }

    @Test
    public void bitTest() {
        byte[] bits = byteToBitArray((byte) 36);
        for (int i = 0; i < bits.length; i++) {
            System.out.println(bits[i]);
        }
    }

    @Test
    public void byte2Bits() {
        // 0 0 1 0 0 1 0 0
        BitSet bitSet = BitSet.valueOf(new byte[]{36});
        System.out.println(bitSet.length());
        for (int i = 0; i < bitSet.length(); i++) {
            System.out.println("bit " + i + ": " + (bitSet.get(i) ? "1" : "0"));
        }

        System.out.println("==========");

        int[] bits = {0, 0, 1, 0, 0, 0, 0, 0};
        BitSet bitSet2 = new BitSet(bits.length);
        for (int i = 0; i < bitSet.length(); i++) {
            bitSet2.set(i, bits[i] > 0);
        }
        System.out.println(bitSet2.toByteArray()[0]);
    }

    @Test
    public void bitMove() {
        byte a = 1;
        byte b = 1 << 1;
        byte c = 1 << 2;
        byte d = 1 << 3;
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
    }
}
