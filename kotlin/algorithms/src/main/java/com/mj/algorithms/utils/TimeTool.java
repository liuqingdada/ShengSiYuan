package com.mj.algorithms.utils;

import kotlin.Unit;
import kotlin.system.TimingKt;

/**
 * Created by andy
 * 2020/4/10.
 * Email: 1239604859@qq.com
 */

public class TimeTool {
    public static void printTimeMillis(Runnable runnable) {
        System.out.println("Start: ");
        long timeMillis = TimingKt.measureTimeMillis(() -> {
            runnable.run();
            return Unit.INSTANCE;
        });
        System.out.println("End: " + timeMillis + " millis");
        System.out.println("-----------");
    }
}
