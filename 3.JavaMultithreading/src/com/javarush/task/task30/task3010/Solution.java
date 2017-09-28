package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/



import java.math.BigInteger;

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        try {
            if (Integer.parseInt(args[0]) < 2) {
                System.out.println(2);
                return;
            }
        }catch (Exception e) {
        }

        BigInteger bigInteger = new BigInteger("0");
            for (int i = 2; i <= 36; i++) {
                try {
                    bigInteger = new BigInteger(args[0], i);
                } catch (Exception e) {
                }
                if (!bigInteger.toString().equals("0")) {
                    System.out.println(i);
                    break;
                }
            }
        if (bigInteger.toString().equals("0"))
            System.out.println("incorrect");
    }
}