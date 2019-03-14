package com.shengsiyuan;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
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

    @Test
    public void sort() {
        Map<String, Set<TicpodDevice>> map = new HashMap<>();

        Set<TicpodDevice> set1 = new HashSet<>();
        set1.add(new TicpodDevice(-20, new byte[0]));
        set1.add(new TicpodDevice(-30, new byte[0]));
        set1.add(new TicpodDevice(-24, new byte[0]));
        map.put("00", set1);

        Set<TicpodDevice> set2 = new HashSet<>();
        set2.add(new TicpodDevice(-40, new byte[0]));
        set2.add(new TicpodDevice(-41, new byte[0]));
        set2.add(new TicpodDevice(-32, new byte[0]));
        map.put("01", set2);

        // 不会改变原始集合的排序
        Observable.from(map.entrySet())
                .flatMap(it -> {
                    Set<TicpodDevice> set = it.getValue();
                    return Observable.from(set);
                })
                .sorted((item1, item2) -> item1.rssi - item2.rssi)
                .subscribe(System.out::println);
        System.out.println(map);
    }

    @Test
    public void sorted() {
        Map<String, Set<TicpodDevice>> map = new HashMap<>();

        Set<TicpodDevice> set1 = new TreeSet<>();
        set1.add(new TicpodDevice(-20, new byte[0]));
        set1.add(new TicpodDevice(-30, new byte[0]));
        set1.add(new TicpodDevice(-24, new byte[0]));
        map.put("00", set1);

        Set<TicpodDevice> set2 = new TreeSet<>();
        set2.add(new TicpodDevice(-40, new byte[0]));
        set2.add(new TicpodDevice(-41, new byte[0]));
        set2.add(new TicpodDevice(-32, new byte[0]));
        map.put("01", set2);

        Observable.from(map.entrySet())
                .map(it -> {
                    System.out.println(it);
                    Set<TicpodDevice> ticpodDevices = it.getValue();
                    TicpodDevice[] toArray = ticpodDevices.toArray(new TicpodDevice[0]);
                    return toArray[ticpodDevices.size() / 2];
                })
                .collect(ArrayList<TicpodDevice>::new, ArrayList::add)
                .subscribe(System.out::println);
    }

    private static class TicpodDevice implements Comparable {
        int rssi;
        byte[] manufacture;

        TicpodDevice(int rssi, byte[] manufacture) {
            this.rssi = rssi;
            this.manufacture = manufacture;
        }

        @Override
        public String toString() {
            return "TicpodDevice{" +
                    "rssi=" + rssi +
                    ", manufacture=" + Arrays.toString(manufacture) +
                    '}';
        }

        @Override
        public int compareTo(@NotNull Object o) {
            if (o instanceof TicpodDevice) {
                TicpodDevice other = (TicpodDevice) o;
                return this.rssi - other.rssi;
            }
            return 0;
        }
    }

    @Test
    public void file0() {
        File[] files = new File("/home/ssd").listFiles();
        System.out.println(files);

        // 1552273741732
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String format = simpleDateFormat.format(new Date(1552273741732L));
        System.out.println(format);
    }
}
