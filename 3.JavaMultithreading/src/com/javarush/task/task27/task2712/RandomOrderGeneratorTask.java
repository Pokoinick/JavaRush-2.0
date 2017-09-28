package com.javarush.task.task27.task2712;

import java.util.List;

/**
 * Created by Станислав on 16.09.2017.
 */
public class RandomOrderGeneratorTask implements Runnable {
    private List<Tablet> tablets;
    private int interval;

    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval) {
        this.tablets = tablets;
        this.interval = interval;
    }

    @Override
    public void run() {
        Tablet tablet = tablets.get((int) (Math.random()*tablets.size()));

        while (true) {
            tablet.createOrder();
            try {
                Thread.sleep(interval);
            } catch (Exception e) {}
        }

    }
}
