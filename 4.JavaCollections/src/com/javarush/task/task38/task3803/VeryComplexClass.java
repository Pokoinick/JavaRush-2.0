package com.javarush.task.task38.task3803;

/* 
Runtime исключения (unchecked exception)
*/

import java.io.BufferedReader;
import java.util.Map;

public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        Object o = new Object();
        Map map = (Map)o;
    }

    public void methodThrowsNullPointerException() {
        Map map = null;
        map.values();
    }

    public static void main(String[] args) {

    }
}
