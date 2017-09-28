package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("Привет   это    строка    на  "));
    }

    public static String getPartOfString(String string) {
        if (string == null || !string.contains(" "))
            throw new TooShortStringException();
        String result = "";
        string = string.replaceAll("  *", " ");
        String[] test = string.split(" ");
        System.out.println(test.length);
        if (test.length < 5)
            throw new TooShortStringException();

        result = test[1] + " " +test[2]+" "+ test[3] + " " + test[4];
        return result;
    }

    public static class TooShortStringException extends RuntimeException {
    }
}
