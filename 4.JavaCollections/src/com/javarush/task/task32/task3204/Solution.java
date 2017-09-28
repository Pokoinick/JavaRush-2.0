package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Random random = new Random();

        char startBottomCase = 97;
        char startUpperCase = 65;
        char startNumbers = 48;

        StringBuilder password = new StringBuilder();
        while (password.length() < 8) {
            password.append((char) (startBottomCase + random.nextInt(25)));
            password.append((char) (startUpperCase + random.nextInt(25)));
            if (password.length() == 8)
                break;
            password.append((char) (startNumbers + random.nextInt(9)));
        }
        byteArrayOutputStream.write(password.toString().getBytes());

        return byteArrayOutputStream;
    }
}