package com.javarush.task.task29.task2913;

import java.util.Random;

/* 
Замена рекурсии
*/

public class Solution {
    private static int numberA;
    private static int numberB;

//    public static String recursion(int a, int b) {
//        if (a > b) {
//            return a + " " + recursion(a - 1, b);
//        } else {
//            if (a == b) {
//                return Integer.toString(a);
//            }
//            return a + " " + recursion(a + 1, b);
//        }
//    }

    public static String getAllNumbersBetween(int a, int b) {
        String result = "";

        if (a == b)
            return Integer.toString(a);
        if(a < b) {

            while (a <= b) {
                result += a + " ";
                a += 1;
            }
            return result.trim();
        }

        if(a > b) {
            while (a >= b) {
                result += a + " ";
                a -= 1;
            }
            return result.trim();
        }


        return result.trim();
    }


    public static void main(String[] args) {
        Random random = new Random();
        numberA = random.nextInt() % 1_000;
        numberB = random.nextInt() % 10_000;
        System.out.println(getAllNumbersBetween(numberA, numberB));
        System.out.println(getAllNumbersBetween(numberB, numberA));
    }
}