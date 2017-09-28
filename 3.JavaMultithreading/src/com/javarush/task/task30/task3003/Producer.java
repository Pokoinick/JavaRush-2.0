package com.javarush.task.task30.task3003;

import java.util.concurrent.TransferQueue;

/**
 * Created by Станислав on 02.06.2017.
 */
public class Producer implements Runnable {
    private TransferQueue<ShareItem> queue;

    public Producer(TransferQueue<ShareItem> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        if (!Thread.currentThread().isInterrupted()) {
            try {
                for (int i = 1; i < 10; i++) {
                    queue.offer(new ShareItem(String.format("ShareItem-%d", i), i));
                    System.out.format("Элемент 'ShareItem-%d' добавлен\n", i);

                    Thread.sleep(100);

                    if (queue.hasWaitingConsumer())
                        System.out.format("Consumer в ожидании!\n");
                }
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
