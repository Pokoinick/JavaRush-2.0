//package com.javarush.task.task27.task2712;
//
//import com.javarush.task.task27.task2712.kitchen.Cook;
//import com.javarush.task.task27.task2712.kitchen.Order;
//import com.javarush.task.task27.task2712.statistic.StatisticManager;
//
//import java.util.Observable;
//import java.util.Observer;
//import java.util.concurrent.LinkedBlockingQueue;
//
///**
// * Created by Станислав on 16.09.2017.
// */
//public class OrderManager implements Observer {
//
//
//    public OrderManager() {
//        Thread t = new Thread() {
//            @Override
//            public void run() {
//                while (!Thread.currentThread().isInterrupted()) {
//                    if (QUEUE.peek() != null)
//                        for (Cook cook : StatisticManager.getInstance().getCooks())
//                            if (!cook.isBusy()) {
//                                final Cook cookFinal = cook;
//                                Thread thr = new Thread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        cookFinal.startCookingOrder(QUEUE.poll());
//                                    }
//                                });
//                                thr.start();
//                                break;
//                            }
//                    try {
//                        Thread.sleep(10);
//                    } catch (InterruptedException e)
//                    {
//                        return;
//                    }
//                }
//
//            }
//        };
//        t.setDaemon(true);
//        t.start();
//    }
//
//    @Override
//    public void update(Observable o, Object arg) {
//        QUEUE.add((Order) arg);
//    }
//
//}
