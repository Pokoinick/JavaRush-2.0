package com.javarush.task.task22.task2203;

/* 
Между табуляциями
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException {
        if (string == null)
            throw new TooShortStringException();
        String[] test = string.split("\\t");
        int lenght = test.length;
        for (String s: test) {
            if (s.isEmpty())
                lenght -= 1;
        }
        String[] test2 = new String[lenght];
        int offset = 0;
        for (int i = 0; i < test.length; i++) {
            if (test[i].isEmpty())
                offset++;
            else
                test2[i-offset] = test[i];
        }
        if (test2.length<3)
            throw new TooShortStringException();


        return string.substring(string.indexOf("\t")+1,string.indexOf("\t", string.indexOf("\t")+1));
    }

    public static class TooShortStringException extends Exception {
    }

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("a\tJavaRush - лучший сервис \tобучения Java\t."));
    }
}
