package com.amaljoyc.faden.basic;

public class SimpleRunnable {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());

        Thread t1 = new Thread(new Runner2());
        t1.start();

        Thread t2 = new Thread(new Runner2());
        t2.start();
    }
}


class Runner2 implements Runnable {
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ", " + "Value=" + i);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}