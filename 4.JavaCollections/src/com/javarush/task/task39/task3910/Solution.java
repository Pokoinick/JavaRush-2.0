package com.javarush.task.task39.task3910;

/* 
isPowerOfThree
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(isPowerOfThree(30));
    }

    public static boolean isPowerOfThree(int n) {
        if (n == 0)
            return false;

        if (n % 3 == 0) {
            return isPowerOfThree(n / 3);
        }
        if (n == 1)
            return true;
        if (n == 3)
            return true;

        return false;
    }
}
