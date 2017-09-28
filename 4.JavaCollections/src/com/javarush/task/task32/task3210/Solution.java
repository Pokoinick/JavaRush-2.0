package com.javarush.task.task32.task3210;

import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) throws Exception {
        RandomAccessFile raf = new RandomAccessFile(args[0], "rw");
        byte[] ar = new byte[args[2].length()];
        raf.seek(Long.valueOf(args[1]));
        raf.read(ar, 0, ar.length);
        raf.seek(raf.length());
        char[] ch = new char[ar.length];
        for (int i = 0; i < ar.length; i++) {
            ch[i] = (char) ar[i];
        }
        String res = String.valueOf(ch);
        String result = "";
        if(res.equals(args[2]))
            result = "true";
        else
            result = "false";
        raf.write(result.getBytes());
        raf.close();

    }
}
