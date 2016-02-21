package com.amaljoyc.faden.cache;

public class CacheApp {
    public static void main(String[] args) throws InterruptedException {
        Processor p = new Processor();
        p.start();

        Thread.sleep(3000);
        System.out.println("***********");

        p.resetFlag();
    }
}

class Processor extends Thread {

    // reads from main memory rather than from cpu cache
    private volatile boolean flag = true;

    public void resetFlag() {
        flag = false;
    }

    public void run() {
        while (flag) {
            System.out.println("Hello");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
