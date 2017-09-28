package com.javarush.task.task36.task3605;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeSet;

/* 
Использование TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        File file = new File(args[0]);
        String data = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        while (reader.ready()) {
            data += reader.readLine();
        }
        data = data.toLowerCase().replaceAll("\\W*", "");
        TreeSet<Character> set = new TreeSet<>();
        for (Character c: data.toCharArray()) {
            set.add(c);
        }
        String result = "";
        if (set.size() > 5) {
            for (int i = 0; i < 5; i++) {
                result += set.pollFirst();
            }
        }
        else {
            int a = set.size();
            for (int i = 0; i < a; i++) {
                result += set.pollFirst();
            }
        }
        System.out.println(result);
    }
}
