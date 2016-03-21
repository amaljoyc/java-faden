package com.amaljoyc.faden.producerconsumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueApp {

    public static void main(String[] args) throws InterruptedException {
        Processor p = new Processor();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    p.producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
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

class Processor {
    // no need to use low level synchronization while using BlockingQueue
    private BlockingQueue queue = new ArrayBlockingQueue(10);
    private Random random = new Random();

    public void producer() throws InterruptedException {
        while (true) {
            // put will block if the queue is full
            queue.put(random.nextInt());
            System.out.println("Adding...size: " + queue.size());
        }
    }

    public void consumer() throws InterruptedException {
        while (true) {
            Thread.sleep(200);
            // take will block if the queue is empty
            queue.take();
            System.out.println("Removing...size: " + queue.size());
        }
    }
}
