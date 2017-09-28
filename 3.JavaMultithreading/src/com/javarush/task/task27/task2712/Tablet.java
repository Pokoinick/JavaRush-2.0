package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.ad.NoVideoAvailableException;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.TestOrder;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Станислав on 16.09.2017.
 */
public class Tablet {
    final int number;
    static Logger logger = Logger.getLogger(Tablet.class.getName());
    private LinkedBlockingQueue<Order> queue;

    public Tablet(int number) {
        this.number = number;
    }

    public void createOrder() {
        try {
            Order order = new Order(this);
            orderHandler(order);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        }
    }

    public void createTestOrder() {
        try {
            TestOrder order = new TestOrder(this);
            orderHandler(order);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        }
    }

    private void orderHandler(Order order) {
        if (!order.isEmpty()) {
            ConsoleHelper.writeMessage(order.toString());
        }
        try {
            AdvertisementManager manager =  new AdvertisementManager(order.getTotalCookingTime() * 60);
            manager.processVideos();
            queue.add(order);
        } catch (NoVideoAvailableException e) {
            logger.log(Level.INFO, "No video is available for the order " + order);
        }
    }

    public void setQueue(LinkedBlockingQueue<Order> orderQueue) {
        this.queue = orderQueue;
    }

    @Override
    public String toString() {
        return "Tablet{" +
                "number=" + number +
                '}';
    }
}
