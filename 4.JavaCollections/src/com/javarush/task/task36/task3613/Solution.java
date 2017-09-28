package com.javarush.task.task36.task3613;


import java.util.concurrent.SynchronousQueue;

/*
Найти класс по описанию
*/
public class Solution {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() throws ClassNotFoundException {
        return SynchronousQueue.class;
    }
}