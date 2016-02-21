package com.amaljoyc.faden.sync;

public class SyncApp {

    // Note: another way would be to use atomic integer (instead of synchronized)
    private int count = 0;

    // uses the lock provided by SyncApp object
    public synchronized int incrementCount() {
        return count++;
    }

    public static void main(String[] args) throws InterruptedException {
        SyncApp app = new SyncApp();
        app.doWork();
    }

    public void doWork() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    incrementCount();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    incrementCount();
                }
            }
        });

        t1.start();
        t2.start();

        // wait until t1 and t2 are dead
        t1.join();
        t2.join();

        System.out.println("Counter is: " + count);
    }
}
