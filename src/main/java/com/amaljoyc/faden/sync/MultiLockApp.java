package com.amaljoyc.faden.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MultiLockApp {

    private Random random = new Random();

    // use different locks to synchronize different list processing
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

    public void processList1() {
        synchronized (lock1) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list1.add(random.nextInt());
        }
    }

    public void processList2() {
        synchronized (lock2) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list2.add(random.nextInt());
        }
    }

    public void processLists() {
        for (int i = 0; i < 1000; i++) {
            processList1();
            processList2();
        }
    }

    public static void main(String[] args) {
        MultiLockApp app = new MultiLockApp();
        long start = System.currentTimeMillis();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                app.processLists();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                app.processLists();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("Time taken: " + (end - start));
        System.out.println("Size of List1: " + app.list1.size());
        System.out.println("Size of List2: " + app.list2.size());
    }
}
