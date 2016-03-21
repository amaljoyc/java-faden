package com.amaljoyc.faden.producerconsumer;

import java.util.LinkedList;
import java.util.Random;

public class WaitNotifyApp {
    public static void main(String[] args) {
        WaitNotifyProcessor p = new WaitNotifyProcessor();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    p.producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    p.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }
}


class WaitNotifyProcessor {

    private static final int LIMIT = 10;

    private Object lock = new Object();
    private LinkedList list = new LinkedList();
    private Random random = new Random();


    public void producer() throws InterruptedException {
        while (true) {
            synchronized (lock) {
                while (list.size() == LIMIT) {
                    System.out.println("Producer waiting...");
                    // call wait on the lock object
                    lock.wait();
                }

                list.add(random.nextInt());
                System.out.println("Adding...size: " + list.size());
                lock.notify();
                System.out.println("Producer notifying!!!");

            }
        }
    }

    public void consumer() throws InterruptedException {
        while (true) {
            synchronized (lock) {
                while (list.size() == 0) {
                    System.out.println("Consumer waiting...");
                    lock.wait();
                }

                list.removeFirst();
                System.out.println("Removing...size: " + list.size());
                // call notify on the lock object
                lock.notify();
                System.out.println("Consumer notifying!!!");
            }

            Thread.sleep(2000);
        }
    }
}