package com.amaljoyc.faden.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolApp {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 5; i++) {
            // submit the job for execution
            executor.submit(new Processor(i));
        }
        // makes sure the executor service is shutdown once all jobs are done
        executor.shutdown();
        System.out.println("All tasks submitted");

        try {
            // wait until all jobs are completed or a day is over
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All tasks completed");
    }
}

class Processor implements Runnable {

    private int id;

    public Processor(int id) {
        this.id = id;
    }

    public void run() {
        System.out.println("Starting " + id);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Completed " + id);
    }
}
