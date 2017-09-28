package com.javarush.task.task08.task0812;

import java.io.*;
import java.util.ArrayList;

/* 
Cамая длинная последовательность
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        //напишите тут ваш код
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i<10; i++) {
            list.add(Integer.parseInt(br.readLine()));
        }
        int counter = 1;
        int number = 1;

        for (int i = 0; i < list.size()-1; i++) {
            if (list.get(i).equals(list.get(i + 1)))
                number++;
            else number = 1;
            if (number > counter)
                counter = number;
        }
        System.out.println(counter);
    }
}