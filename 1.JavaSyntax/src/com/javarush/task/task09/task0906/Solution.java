package com.javarush.task.task09.task0906;

/* 
Логирование стек трейса
*/
import java.lang.*;

public class Solution {
    public static void main(String[] args) {
        log("In main method");
    }

    public static void log(String s) {
        //напишите тут ваш код
        StackTraceElement[] a = Thread.currentThread().getStackTrace();
        System.out.println(a[2].getClassName()+": "+ a[2].getMethodName()+": "+s);
    }
}
