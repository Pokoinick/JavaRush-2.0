package com.javarush.task.task33.task3308;

import java.util.List;

/**
 * Created by Станислав on 12.07.2017.
 */
public class Shop {
    public Shop(){}
    public int count;
    public Goods goods;
    public double profit;
    public String[] secretData;

    public static class Goods {
        public List<String> names;
    }
}