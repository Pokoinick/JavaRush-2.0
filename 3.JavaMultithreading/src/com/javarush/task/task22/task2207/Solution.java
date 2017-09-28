package com.javarush.task.task22.task2207;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/* 
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();
    public static ArrayList<String> wordsList = new ArrayList<>();

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader fileReader = new BufferedReader(new FileReader(reader.readLine()))){
            String s;
            StringBuilder file = new StringBuilder();
            while ((s = fileReader.readLine()) != null) {
                file.append(s+" ");
            }
            String[] words = file.toString().split(" ");
            for (String string: words) {
                wordsList.add(string);
            }
            for (int i = 0; i < wordsList.size()-1; i++) {
                StringBuilder comparator = new StringBuilder(wordsList.get(i));
                for (int j = i; j < wordsList.size()-1; j++) {
                    StringBuilder second = new StringBuilder(wordsList.get(j+1));
                    if(comparator.reverse().toString().equals(second.toString()) && comparator.length() != 0 && second.length() != 0) {
                        Pair pair = new Pair();
                        pair.first = comparator.reverse().toString();
                        pair.second = second.toString();
                        result.add(pair);
                        wordsList.remove(j+1);
                        wordsList.remove(i);
                        i--;
                        break;
                    }
                    else
                        comparator.reverse();

                }
            }
            for (Pair p : result) {
                System.out.println(p.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class Pair {
        String first;
        String second;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                    first == null && second != null ? second :
                    second == null && first != null ? first :
                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
