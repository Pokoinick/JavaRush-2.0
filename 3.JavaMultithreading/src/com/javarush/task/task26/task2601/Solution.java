package com.javarush.task.task26.task2601;

import java.util.Arrays;
import java.util.Comparator;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    static double median;

    public static void main(String[] args) {
    }

    public static Integer[] sort(Integer[] array) {
        //implement logic here
        Arrays.sort(array);

        if(array.length >= 2)
            median = array.length%2 == 0. ? (array[array.length/2-1]+array[array.length/2])/2d : array[array.length/2];

        Arrays.sort(array, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {

                return Math.abs(median-o1) > Math.abs(median-o2) ? 1 : Math.abs(median-o1) == Math.abs(median-o2) ? -(o2.compareTo(o1)) : -1;
            }
        });
        return array;
    }

}
