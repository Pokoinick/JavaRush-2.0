package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Станислав on 16.09.2017.
 */
public class Cook extends Observable implements Runnable{
    private String name;
    private boolean busy;
    private LinkedBlockingQueue<Order> queue;
    private boolean stopped = false;
    private boolean caught = false;

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public void run()
    {
        while (!stopped)
        {
            try
            {
                startCookingOrder(queue.take());
            }
            catch (InterruptedException e)
            {
                caught = true;
            }
            if (caught && queue.isEmpty())
                stopped = true;
        }
    }

    public void startCookingOrder(Order order) {
        busy = true;
        ConsoleHelper.writeMessage("Start cooking - " + order + ", cooking time " + order.getTotalCookingTime() + "min");
        StatisticManager.getInstance().register(
                new CookedOrderEventDataRow(
                        "",
                        name,
                        order.getTotalCookingTime() * 60,
                        order.getDishes()));
        setChanged();
        notifyObservers(order);
        try {
            Thread.sleep(order.getTotalCookingTime() * 10);
        } catch (Exception e) {}
        busy = false;
    }

    public void setQueue(LinkedBlockingQueue<Order> orderQueue) {
        this.queue = orderQueue;
    }

    public boolean isBusy() {
        return busy;
    }

    @Override
    public String toString() {
        return name;
    }
}
