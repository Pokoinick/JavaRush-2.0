package com.javarush.task.task20;

import java.util.TreeSet;

/*
Алгоритмы-числа
*/
public class Solution_2025 {

    static long[][] first = new long[20][10];
    static {
        first[0][0] = 0L;
        first[0][1] = 1L;
        first[0][2] = 2L;
        first[0][3] = 3L;
        first[0][4] = 4L;
        first[0][5] = 5L;
        first[0][6] = 6L;
        first[0][7] = 7L;
        first[0][8] = 8L;
        first[0][9] = 9L;

        for (int i = 1; i<20; i++) {
            for (int j = 0; j < 10; j++) {
                first[i][j] = first[0][j] * first[i-1][j];
            }
        }
  }


    public static int[] getNumbers(long N) {

        TreeSet<Long> armstorngNumbers = new TreeSet<>();
        long tempGlobal = 0L; // переменная для перебора чисел
        long sum = 0L;
        long sum10 = 0L;
        long sum100 = 0L;



            while (true) {

                if(tempGlobal % 10 != 9)
                    tempGlobal++;
                else {
                    String miss= "";
                    long temp = tempGlobal/10 + 1L;
                    while (temp %10 == 0L)
                        temp /= 10L;
                    miss = miss.concat(temp+"");
                    while (miss.length() < (tempGlobal+"").length())
                        miss = miss.concat(temp % 10+"");
                    if (miss.length() < (tempGlobal+1+"").length())
                        miss = miss.concat(temp % 10+"");
                    tempGlobal = Long.parseLong(miss);
                }

                if (tempGlobal>N)
                    break;

                sum = getSum(tempGlobal, 1);
                if (sum == tempGlobal && sum <= N)
                    armstorngNumbers.add(tempGlobal);
                else if (sum == getSum(sum, 1) && sum < N)
                    armstorngNumbers.add(sum);

                sum10 = getSum(tempGlobal, 2);
                if (sum10 == getSum(sum10, 1) && sum10 < N)
                    armstorngNumbers.add(sum10);

                sum100 = getSum(tempGlobal, 3);
                if (sum100 == getSum(sum100, 1) && sum100 < N)
                    armstorngNumbers.add(sum100);
            }

        int[] result = new int[armstorngNumbers.size()];
        int j = 0;
        for (long i : armstorngNumbers) {
            result[j] = (int)i;
            j++;
        }

        return result;
    }

    public static long getSum (long tempGlobal, int factor) {

        long sum = 0;
        String temp = (tempGlobal + "");
        if (factor==1) {
            for (int i = 0; i < temp.length(); i++) {
                try {
                    sum += first[temp.length() - 1][Integer.parseInt(temp.charAt(i) + "")];
                }
                catch (NumberFormatException e) {
                    System.out.println(tempGlobal);
                }
            }
            return sum;
        }

        if (factor==2) {
            for (int i = 0; i < temp.length(); i++) {
                sum += first[temp.length()][Integer.parseInt(temp.charAt(i) + "")];
            }
            return sum;
        }

        if (factor==3) {
            for (int i = 0; i < temp.length(); i++) {
                sum += first[temp.length() + 1][Integer.parseInt(temp.charAt(i) + "")];
            }
            return sum;
        }
        return 0L;
    }


    public static void main(String[] args) {
        long timeStart = System.currentTimeMillis();
        long memoryStart1 = Runtime.getRuntime().totalMemory();
        long memoryEnd1 = Runtime.getRuntime().freeMemory();
        int[] result = getNumbers(100_000_000_000_000L);
        long timeEnd = System.currentTimeMillis();
        long memoryStart2 = Runtime.getRuntime().totalMemory();
        long memoryEnd2 = Runtime.getRuntime().freeMemory();

        System.out.println(result.length);
        System.out.println(timeEnd - timeStart + "ms");
        System.out.println((memoryStart1 - memoryEnd1) / 1024 + "kb");
        System.out.println((memoryStart2 - memoryEnd2) / 1024 + "kb");

        for (long l : result) {
            System.out.print(l+" ");
        }
    }
}