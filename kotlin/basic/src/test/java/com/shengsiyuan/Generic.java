package com.shengsiyuan;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

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
        Observable.fromIterable(map.entrySet())
                .flatMap(it -> {
                    Set<TicpodDevice> set = it.getValue();
                    return Observable.fromIterable(set);
                })
                .sorted(Comparator.comparingInt(item -> item.rssi))
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

        Observable.fromIterable(map.entrySet())
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

    @Test
    public void limit0() {
        Observable.just(100)
                .take(2)
                .subscribe(it -> {
                    System.out.println(it);
                });

        Observable.empty()
                .take(2)
                .subscribe(it -> {
                    System.out.println(it);
                }, e -> {
                    e.printStackTrace();
                }, () -> {
                    System.out.println("complete");
                });
    }

    @Test
    public void stringSort() throws Exception {
        String mac1 = "00:00:46:66:AB:BA".replaceAll(":", "").toLowerCase();
        String mac2 = "00:00:46:66:BA:AB".replaceAll(":", "").toLowerCase();
        String[] macs = {mac2, mac1};
        Arrays.sort(macs);

        String deviceId = macs[0] + macs[1];
        System.out.println(deviceId);

        String md5 = DigestEncodingUtils.md5(deviceId);
        System.out.println(md5);
    }

    @Test
    public void buildManufacture() {
        String goneName = "FDA7";
        String mac1 = "000046663001";
        String mac2 = "000046663002";
        List<Byte> manufacture = new ArrayList<>();
        for (byte b : IoUtils.hexStr2Bytes(goneName)) {
            manufacture.add(b);
        }
        for (byte b : IoUtils.hexStr2Bytes(mac1)) {
            manufacture.add(b);
        }
        for (byte b : IoUtils.hexStr2Bytes(mac2)) {
            manufacture.add(b);
        }
        manufacture.add((byte) 30); // power
        manufacture.add((byte) 40);
        manufacture.add((byte) 50);
        manufacture.add((byte) 1); // type
        manufacture.add((byte) 0);
        manufacture.add((byte) -64);
        manufacture.add((byte) 0);
        manufacture.add((byte) 0);
        manufacture.add((byte) 0);
        manufacture.add((byte) 0);
        manufacture.add((byte) 0);
        manufacture.add((byte) 1);
        byte[] manu = new byte[26];
        for (int i = 0; i < manu.length; i++) {
            manu[i] = manufacture.get(i);
        }
        System.out.println(IoUtils.bytes2Hex(manu));
    }

    @Test
    public void blockingTest() {
        LinkedBlockingQueue<Runnable> linkedBlockingQueue = new LinkedBlockingQueue<>();
        ExecutorService putService = Executors.newSingleThreadExecutor();
        ExecutorService takeService = Executors.newSingleThreadExecutor();

        putService.execute(() -> {
            try {
                Thread.sleep(10000);
                while (true) {
                    linkedBlockingQueue.put(() -> System.err.println("--------"));
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        takeService.execute(() -> {
            try {
                while (true) {
                    System.out.println("start take: " + System.currentTimeMillis());
                    Runnable runnable = linkedBlockingQueue.take();
                    System.out.println("end take: " + System.currentTimeMillis());
                    takeService.execute(runnable);
                    System.out.println();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        synchronized (this) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
