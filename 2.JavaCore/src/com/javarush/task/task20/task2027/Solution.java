package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.List;

/* 
Кроссворд
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        List<Word> result = detectAllWords(crossword, "home", "same", "emoh", "emas", "fderlk", "klredf", "fulmp", "poeejj", "jjeeop",
                "pmluf", "kovhj", "jhvok", "lprr", "rrpl", "lprr", "voel", "lock", "r", "re", "eo", "oe", null, "", " ");

        for (Word eee: result) {
            System.out.println(eee.toString());
        }
        System.out.println(result.size());
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        ArrayList<Word> result = new ArrayList<>();

        for (String word : words) {
            if (word == null)

                continue;
            if (word.length() == 0)
                continue;
            for (int i = 0; i < crossword.length; i++) {
                for (int j = 0; j < crossword[i].length; j++) {
                    int startX = j;
                    int startY = i;
                    boolean endDetected = false;
                    if (crossword[i][j] == word.charAt(0)) {

                        if (word.length() == 1) {
                            Word testing = new Word(word);
                            testing.startX=j;
                            testing.startY=i;
                            testing.endX = j;
                            testing.endY = i;
                            result.add(testing);
                            continue;
                        }

                        int xStart = -1;
                        int yStart = -1;
                        int xLength = 2;
                        int yLength = 2;

                        if(j + 1 >= crossword[i].length && i+1 >= crossword.length) {
                            xLength = 1;
                            yLength = 1;
                            xStart = -1;
                            yStart = -1;
                        }
                        else if(j - 1  < 0 && i - 1 < 0) {
                            xLength = 2;
                            yLength = 2;
                            xStart = 0;
                            yStart = 0;

                        }
                        else if(j + 1 >= crossword[i].length && i - 1 < 0) {
                            xLength = 1;
                            yLength = 2;
                            xStart = -1;
                            yStart = 0;
                        }
                        else if(j - 1 < 0 && i + 1 >= crossword.length) {
                            xLength = 2;
                            yLength = 1;
                            xStart = 0;
                            yStart = -1;
                        }

                        else if(j - 1 < 0) {
                            xLength = 2;
                            yLength = 2;
                            xStart = 0;
                            yStart = -1;
                        }
                        else if(j + 1 >= crossword[i].length) {
                            xLength = 1;
                            yLength = 2;
                            xStart = -1;
                            yStart = -1;
                        }
                        else if(i + 1 >= crossword.length) {
                            xLength = 2;
                            yLength = 1;
                            xStart = -1;
                            yStart = -1;
                        }
                        else if(i - 1 < 0) {
                            xLength = 2;
                            yLength = 2;
                            xStart = -1;
                            yStart = 0;
                        }

                        for (int k = yStart; k < yLength; k++) {
                            for (int l = xStart; l < xLength; l++) {
                                if(crossword[startY+k][startX+l] == word.charAt(1)) {
                                    int endX = startX;
                                    int endY = startY;
                                    for (int m = 0; m < word.length()-1; m++) {
                                        endX += l;
                                        endY += k;
                                        if (endX < 0 || endX >= crossword[i].length)
                                            break;
                                        if (endY < 0 || endY >= crossword.length)
                                            break;
                                        if (crossword[endY][endX] != word.charAt(1+m))
                                            break;
                                        if(crossword[endY][endX] == word.charAt(word.length()-1)&& m == word.length()-2) {
                                            endDetected = true;
                                            Word testing = new Word(word);
                                            testing.startX=j;
                                            testing.startY=i;
                                            testing.endX = endX;
                                            testing.endY = endY;
                                            result.add(testing);
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if (endDetected) {
                        endDetected = false;
                    }

                }
            }
        }

        return result;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
