package com.javarush.task.task25.task2502;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/* 
Машину на СТО не повезем!
*/
public class Solution {
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        public Car() throws Exception {
            //init wheels here
            wheels = new LinkedList<>();
            if(loadWheelNamesFromDB().length != Wheel.values().length)
                throw new IllegalArgumentException();
            for (String str: loadWheelNamesFromDB()) {
                Wheel wheel = Wheel.valueOf(str);
                wheels.add(wheel);
            }
        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }

    public static void main(String[] args) {
    }
}
