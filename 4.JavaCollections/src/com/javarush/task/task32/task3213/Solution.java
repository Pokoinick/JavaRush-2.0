package com.javarush.task.task32.task3213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/* 
Шифр Цезаря
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("Khoor Dpljr");
        System.out.println(decode(reader, -3));  //Hello Amigo

    }

    public static String decode(StringReader reader, int key) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(reader)) {
            char[] code;
            code = bufferedReader.readLine().toCharArray();
            for (int i = 0; i < code.length; i++) {
                code[i] += key;
            }
            return String.valueOf(code);

        } catch (Exception e){
            return "";
        }
    }
}
