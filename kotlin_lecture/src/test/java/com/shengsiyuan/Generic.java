package com.shengsiyuan;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by suhen
 * 18-11-26.
 * Email: 1239604859@qq.com
 */

public class Generic {
    @Test
    public void main() {
        List<?> list = new ArrayList<>();

        List<String> strs = new ArrayList<>();

        List<? super String> strs2 = new ArrayList<>();

        //list.addAll(strs);

        list = strs;

        strs2 = strs;
    }

    @Test
    public void delay() throws InterruptedException {
        Observable.just(0)
                .observeOn(Schedulers.io())
                .doOnNext(it -> {
                    System.out.println("ennable ota");
                })
                .delay(3, TimeUnit.SECONDS)
                .doOnNext(it -> {
                    System.out.println("set mtu 64");
                })
                .delay(3, TimeUnit.SECONDS)
                .doOnNext(it -> {
                    System.out.println("enable RWCP");
                })
                .delay(3, TimeUnit.SECONDS)
                .doOnNext(it -> {
                    System.out.println("start ota");
                })
                .delay(3, TimeUnit.SECONDS)
                .doOnNext(it -> {
                    System.out.println("check time out");
                })
                .subscribe();
        Thread.sleep(15 * 1000);
    }
}
