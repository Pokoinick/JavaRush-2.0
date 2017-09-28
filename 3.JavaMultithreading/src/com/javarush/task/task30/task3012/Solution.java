package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/


public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(1000);
    }

    public void createExpression(int number) {
        //1, 3, 9, 27, 81, 243, 729, 2187;
        String number2 = Integer.toUnsignedString(number, 3);
        StringBuilder builder = new StringBuilder();
        builder.append(number + " =");

        int[] numbers = new int[number2.length() + 1];
        for (int i = 0; i < number2.length(); i++) {
            numbers[i+1] = Integer.parseInt(number2.charAt(i) + "");
        }

        for (int i = numbers.length - 1 ; i >= 0; i--) {
            if (numbers[i] == 2) {
                numbers[i] = -1;
                if (i - 1 < 0)
                    numbers[0] = 1;
                else {
                    numbers[i - 1] = numbers[i - 1] + 1;
                }
            } else if (numbers[i] == 3) {
                numbers[i] = 0;
                if (i - 1 < 0)
                    numbers[0] = 1;
                else {
                    numbers[i - 1] = numbers[i - 1] + 1;
                }
            }
        }

        for (int i = numbers.length-1; i >=0; i--) {
            if (numbers[i] != 0) {
                if (numbers[i] < 0)
                    builder.append(" - " + Math.abs(numbers[i]) * (int) Math.pow(3, numbers.length - i - 1));
                else
                    builder.append(" + " + numbers[i] * (int) Math.pow(3, numbers.length - i - 1));
            }
        }
        System.out.println(builder);
    }
}
