package com.javarush.task.task21.task2101;

import java.util.Arrays;

/*
Определяем адрес сети
*/
public class Solution {
    public static void main(String[] args) {
        byte[] ip = new byte[]{(byte) 192, (byte) 168, 1, 2};
        byte[] mask = new byte[]{(byte) 255, (byte) 255, (byte) 255, 0};
        byte[] netAddress = getNetAddress(ip, mask);
        print(ip);          //11000000 10101000 00000001 00000010
        print(mask);        //11111111 11111111 11111110 00000000
        print(netAddress);  //11000000 10101000 00000000 00000000
    }

    public static byte[] getNetAddress(byte[] ip, byte[] mask) {
        byte[] result = new byte[4];
        for (int i = 0; i < 4; i++) {
            result[i] = (byte)(ip[i] & mask[i]);
        }

        return result;
    }

    public static void print(byte[] bytes) {
        String adress = "";
        int temp;
        for (byte b: bytes) {
            StringBuilder innerString = new StringBuilder();
            if (b < 0) {
                temp = 256 + b;
                innerString.append(Integer.toBinaryString(temp));
            }
            else {
                temp = b;
                innerString.append(Integer.toBinaryString(temp));
            }
            innerString.reverse();
            while (innerString.length()<8){
                innerString.append("0");
            }
            innerString.reverse();
            adress += innerString.toString()+" ";
        }

        System.out.println(adress);

    }
}
