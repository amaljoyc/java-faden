package com.amaljoyc.faden.producerconsumer;

public class WaitNotifySimpleApp {
    public static void main(String[] args) {
        WaitNotifySimpleProcessor p = new WaitNotifySimpleProcessor();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    p.waitMethod();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    p.notifyMethod();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }
}

class WaitNotifySimpleProcessor {

    public void waitMethod() throws InterruptedException {
        // either synchronize on this or create a separate lock object
        synchronized (this) {
            System.out.println("Waiting...");
            wait();
            System.out.println("Resuming after wait");
        }
    }

    public void notifyMethod() throws InterruptedException {
        synchronized (this) {
            Thread.sleep(3000);
            System.out.println("Notifying...");
            notify();
            // even though notify is done, this synchronized block will complete before waitMethod resumes again
            Thread.sleep(2000);
            System.out.println("After notify");
        }
        // waitMethod resumes here
        Thread.sleep(2000);
        System.out.println("Outside synchronized");
    }
}
