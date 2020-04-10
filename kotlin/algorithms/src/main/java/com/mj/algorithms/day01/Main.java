package com.mj.algorithms.day01;

import com.mj.algorithms.utils.TimeTool;

/**
 * Created by andy
 * 2020/4/10.
 * Email: 1239604859@qq.com
 * <p>
 * 复杂度
 * 时间复杂度(time complexity) 估算程序指令的执行次数
 * 空间复杂度(space complexity) 估算所需占用的存储空间
 * <p>
 * 斐波那契数列(fibonacci number)
 * 0 1 1 2 3 5 8 13 ...
 * <p>
 * <p>
 * 大O表示法 (Big O)
 * 一般用大O表示法来描述复杂度, 它表示的是数据规模n对应的复杂度
 * 忽略常数, 系数, 低阶
 * 9                       --- O(1)
 * 2n + 3                  --- O(n)
 * n^2 + 2n + 6            --- 0(n^2)
 * 4n^3 + 3n^2 + 22n + 100 --- O(n^3)
 * 2^n                     --- O(2^n)
 * 对数阶一般省略底数
 * log2(n), log9(n)统称为log(n)
 * log5(n)                               --- O(logn)
 * 1 + 3*log2(n) + 2 * nlog2(n)          --- O(nlogn)
 * 多个数据情况, 比如两个for, 一个n次, 一个m次 --- O(n + m)
 */

public class Main {

    /**
     * O(2^n)
     */
    private static long fib(long n) {
        if (n <= 1) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }

    /**
     * O(n)
     */
    private static long fib2(long n) {
        if (n <= 1) {
            return n;
        }
        long first = 0;
        long second = 1;
        for (int i = 0; i < n - 1; i++) {
            long sum = first + second;
            first = second;
            second = sum;
        }
        return second;
    }

    /**
     * F(n) = 1/√5 * { [(1 + √5) / 2]^n - [(1 - √5) / 2]^n }
     * <p>
     * 事实证明, 这个公式不是很准
     */
    private static long fib3(int n) {
        double c = Math.sqrt(5);
        double ret = (Math.pow((1 + c) * 0.5, n) - Math.pow((1 - c) * 0.5, n)) / c;
        System.out.println(ret);
        return (long) ret;
    }

    public static void main(String[] args) {
        TimeTool.printTimeMillis(() -> {
//            System.out.println(fib(45));
        });
        TimeTool.printTimeMillis(() -> {
            System.out.println(fib2(640));
        });
        TimeTool.printTimeMillis(() -> {
            System.out.println(fib3(640));
        });
    }
}
