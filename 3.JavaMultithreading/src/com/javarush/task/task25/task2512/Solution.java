package com.javarush.task.task25.task2512;

import java.util.ArrayList;

/*
Живем своим умом
*/
public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();
        Throwable a = e;
        ArrayList<String> list = new ArrayList<>();
        while(true){
            list.add(a.getClass().getTypeName()+": "+a.getMessage());
            if(a.getCause() == null)
                break;
            a = a.getCause();
        }
        for (int i = list.size(); i > 0; i--) {
            System.out.println(list.get(i-1));
        }
    }

    public static void main(String[] args) throws Exception {
        Thread.currentThread().setUncaughtExceptionHandler(new Solution());
        throw new Exception("1", new RuntimeException("2", new IllegalArgumentException("3")));
    }
}
