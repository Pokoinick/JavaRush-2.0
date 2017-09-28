package com.javarush.task.task22.task2212;

/* 
Проверка номера телефона
*/
public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        if(telNumber == null || telNumber.isEmpty())
            return false;
        if(telNumber.charAt(0) == '+') {
            int counter = 0;
            for (char c : telNumber.toCharArray()) {
                if(Character.isDigit(c))
                    counter++;
            }
            if (counter != 12)
                return false;
        }

        if(telNumber.charAt(0) != '+') {
            int counter = 0;
            for (char c : telNumber.toCharArray()) {
                if(Character.isDigit(c))
                    counter++;
            }
            if (counter != 10)
                return false;
        }
        if(telNumber.matches("\\+\\d{12}$|\\+?\\d*(\\(\\d{3}\\))?(\\-?\\d+)?\\-?\\d*$"))
            return true;

        return false;
    }

    public static void main(String[] args) {
    }
}
