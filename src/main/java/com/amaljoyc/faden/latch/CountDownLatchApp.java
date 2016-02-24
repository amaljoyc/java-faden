package com.amaljoyc.faden.latch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchApp {
    public static void main(String[] args) {

        // counter will start at 5
        CountDownLatch latch = new CountDownLatch(5);
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executor.submit(new Processor(latch));
        }

        // shutdown is required to kill the threads in the pool.
        // ExecutorService spawns non-daemon threads so that it doesn't stop until shutdown is called
        executor.shutdown();

        try {
            // wait until the count reaches 0
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All jobs completed!!");
    }
}

class Processor implements Runnable {
    private CountDownLatch latch;

    public Processor(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
        System.out.println("Started job: " + Thread.currentThread().getId());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // decrements the counter by 1
        latch.countDown();
        System.out.println("Finished job: " + Thread.currentThread().getId());
    }
}
