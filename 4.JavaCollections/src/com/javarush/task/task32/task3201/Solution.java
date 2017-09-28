package com.javarush.task.task32.task3201;

import java.io.RandomAccessFile;

/*
Запись в существующий файл
*/
public class Solution {
    public static void main(String... args) throws Exception {
        RandomAccessFile raf = new RandomAccessFile(args[0], "rw");
        if (Long.valueOf(args[1]) < raf.length()) {
            raf.seek(Long.valueOf(args[1]));
            raf.write(args[2].getBytes());
        }
        else {
            raf.seek(raf.length());
            raf.write(args[2].getBytes());
        }
        raf.close();

    }
}
