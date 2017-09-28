package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Станислав on 16.09.2017.
 */
public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return reader.readLine();

    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        List<Dish> dishes = new ArrayList<>();
        writeMessage("Choose dishes:");
        writeMessage(Dish.allDishesToString());

        while (true) {
           try {
               String s = readString();

               if (s.equalsIgnoreCase("exit"))
                   break;

               dishes.add(Dish.valueOf(s));
           } catch (IllegalArgumentException e) {
               writeMessage("We don't have such dish!");
           }

        }
        return dishes;
    }


}
