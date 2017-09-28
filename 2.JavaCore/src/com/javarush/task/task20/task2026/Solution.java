package com.javarush.task.task20.task2026;

/* 
Алгоритмы-прямоугольники
*/
public class Solution {
    public static void main(String[] args) {
        byte[][] a = new byte[][]{
                {1, 0, 1, 0},
                {0, 1, 0, 1},
                {1, 0, 1, 0},
                {0, 1, 0, 1}
        };
        int count = getRectangleCount(a);
        System.out.println("count = " + count + ". Должно быть 2");
    }

    public static int getRectangleCount(byte[][] a) {
        int x = 0;
        int y = 0;
        byte[][] b = new byte[a.length][a[0].length];
        int count = 0;
        int time = 0;

        while (true) {
            for (y = 0; y < a.length; y++) {
                for (x = 0; x < a[y].length; x++) {
                    if (a[y][x] == 1 && b[y][x] == 0) {
                        count++;
                        int oldX = x;
                        b[y][x] = 1;

                        for (int i = x; i < a[y].length - 1; i++) {
                            if (a[y][i] != a[y][i + 1]) {
                                x = i;
                                break;
                            }
                            else
                                b[y][i + 1] = 1;
                        }

                        for (int i = y; i < a.length-1; i++) {
                            if (a[i][x] != a[i+1][x]) {
                                y = i;
                                break;
                            }
                            else {
                                b[i + 1][x] = 1;
                                b[i + 1][oldX] = 1;
                            }

                        }

                    }
                }

            }
            time++;
            if (time != count)
                break;
        }
        return count;
    }
}
