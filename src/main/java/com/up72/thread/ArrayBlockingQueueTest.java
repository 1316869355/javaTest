package com.up72.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
public class ArrayBlockingQueueTest {
//    static ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(9);

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue queue = new ArrayBlockingQueue(10);

        for (int i = 0; i < 10; i++)
            new Thread(new ThreadProducer(queue)).start();
        for (int i = 0; i < 10; i++)
            new Thread(new ThreadConsumer(queue)).start();
    }
}

class ThreadProducer implements Runnable {
    ThreadProducer(BlockingQueue queue) {
        this.queue = queue;
    }

    BlockingQueue queue;
    static int cnt = 0;

    public void run() {
        String cmd;
        while (true) {
            cmd = "" + (cnt);
            cnt = (cnt + 1) & 0xFFFFFFFF;
            try {
                queue.put(cmd);
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class ThreadConsumer implements Runnable {
    ThreadConsumer(BlockingQueue queue) {
        this.queue = queue;
    }

    BlockingQueue queue;

    public void run() {
        String cmd;
        while (true) {
            try {
                System.out.println(queue.take());
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
