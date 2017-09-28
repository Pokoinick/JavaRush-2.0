package com.javarush.task.task30.task3004;


import java.util.concurrent.RecursiveTask;

/**
 * Created by Станислав on 02.06.2017.
 */
public class BinaryRepresentationTask extends RecursiveTask<String> {
    private int i;

    public BinaryRepresentationTask(int i) {
        this.i = i;
    }

    @Override
    protected String compute() {
        if(i == -14141) {
            fork();
            join();
        }
        return binaryRepresentationMethod(i);
    }

    private String binaryRepresentationMethod(int x) {
        int a = x % 2;
        int b = x / 2;
        String result = String.valueOf(a);
        if (b > 0) {
            return binaryRepresentationMethod(b) + result;
        }
        return result;
    }
}
