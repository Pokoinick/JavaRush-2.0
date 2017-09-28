package com.javarush.task.task27.task2712.kitchen;

/**
 * Created by Станислав on 16.09.2017.
 */
public enum  Dish {
    Fish(25),
    Steak(30),
    Soup(15),
    Juice(5),
    Water(3);

    private int duration;

    Dish(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public static String allDishesToString() {
        String result = "";
        for (Dish d: Dish.values()) {
            result += d + ", ";
        }
        return result.substring(0, result.length()-1);
    }
}
