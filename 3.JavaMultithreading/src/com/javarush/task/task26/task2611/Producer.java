package com.javarush.task.task26.task2611;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Станислав on 23.05.2017.
 */
public class Producer implements Runnable {
    private ConcurrentHashMap <String, String> map;

    public Producer(ConcurrentHashMap map) {
        this.map = map;
    }
    @Override
    public void run() {
        int i = 1;

        try{
            while (true) {
                String s = "Some text for " + i;
                Thread.sleep(500);
                map.put(Integer.toString(i++), s);

            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+ " thread was terminated");
        }
    }
}
