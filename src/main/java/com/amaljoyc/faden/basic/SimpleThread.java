package com.amaljoyc.faden.basic;

public class SimpleThread {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());

        Thread t1 = new Runner1();
        t1.start();

        Thread t2 = new Runner1();
        t2.start();
    }
}

class Runner1 extends Thread {
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(this.getName() + ", " + "Value=" + i);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
