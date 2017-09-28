package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Станислав on 16.09.2017.
 */
public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;
    private static final LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        List<Tablet> tablets = new ArrayList<>();
        Cook cook = new Cook("Amigo");
        Cook cook2 = new Cook("Cook2");
        cook.setQueue(orderQueue);
        cook2.setQueue(orderQueue);
        Waiter waiter = new Waiter();
        cook.addObserver(waiter);
        cook2.addObserver(waiter);

        for (int i = 0; i < 5; i++) {
            Tablet tablet = new Tablet(i);
            tablet.setQueue(orderQueue);
            tablets.add(tablet);
        }
        Thread cookThread = new Thread(cook);
        cookThread.start();

        Thread cookThread2 = new Thread(cook2);
        cookThread2.start();

        Thread thread = new Thread(new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL));
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {}
        thread.interrupt();

        cookThread.interrupt();
        cookThread2.interrupt();

        try {
            cookThread.join();
            cookThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }

}
