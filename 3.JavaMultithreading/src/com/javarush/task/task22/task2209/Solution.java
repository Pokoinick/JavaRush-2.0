package com.javarush.task.task22.task2209;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


/*
Составить цепочку слов
*/
public class Solution {
    public static void main(String[] args) {
        //...
        String[] words = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader fileReader = new BufferedReader(new FileReader(reader.readLine()))){
            String s;
            StringBuilder file = new StringBuilder();
            while ((s = fileReader.readLine()) != null) {
                file.append(s).append(" ");
            }
            words = file.toString().split(" ");
            Arrays.sort(words);
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder result = getLine(words);
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        ArrayList<String> temp = new ArrayList<>();

        StringBuilder line = new StringBuilder();

        if(words == null || words.length == 0)
          return line;

        temp.addAll(Arrays.asList(words));

        if(line.toString().isEmpty()) {
            line.append(temp.get(0));
            temp.remove(0);
        }

        while (true){
            String test = line.toString();
            for (int j = 0; j < temp.size(); j++) {
                String second = temp.get(j);
                if(line.reverse().charAt(0) == second.charAt(0) || line.charAt(0) == second.toLowerCase().charAt(0)) {
                    line.reverse();
                    line.append(" ").append(second);
                    temp.remove(j);
                    break;
                }
                else
                    line.reverse();
            }
            if (test.equals(line.toString())) {
                for (String string : temp) {
                    line.append(" ").append(string);
                }
                break;
            }

        }
        return line;
    }
}
