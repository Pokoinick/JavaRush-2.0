package com.javarush.task.task26.task2610;


import java.util.concurrent.BlockingQueue;

/**
 * Created by Станислав on 23.05.2017.
 */
public class Consumer implements Runnable {
    private BlockingQueue queue;

    public Consumer(BlockingQueue queue){
        this.queue = queue;
    }
    @Override
    public void run() {
        try {
            while (true) {
                System.out.println(queue.take().toString());
            }
        } catch (InterruptedException e) {
        }
    }
}
