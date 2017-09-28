package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Станислав on 16.09.2017.
 */
public class TestOrder extends Order {

    public TestOrder(Tablet tablet) throws IOException {
        super(tablet);
    }

    @Override
    protected void initDishes() {
        dishes = new ArrayList<>();
        ConsoleHelper.writeMessage(Dish.allDishesToString());
        for (int i = 0; i < (int) (Math.random() * 10); i++) {
            dishes.add(Dish.values()[(int) (Math.random() * Dish.values().length)]);
        }
    }
}
