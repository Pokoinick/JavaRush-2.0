package com.javarush.task.task21.task2106;

import java.util.Date;
import java.util.HashSet;

/* 
Ошибка в equals/hashCode
*/
public class Solution {
    private int anInt;
    private String string;
    private double aDouble;
    private Date date;
    private Solution solution;

    public Solution(int anInt, String string, double aDouble, Date date, Solution solution) {
        this.anInt = anInt;
        this.string = string;
        this.aDouble = aDouble;
        this.date = date;
        this.solution = solution;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Solution solution1 = (Solution) o;

        if (anInt != solution1.anInt) return false;
        if (Double.compare(solution1.aDouble, aDouble) != 0) return false;
        if (string != null ? !string.equals(solution1.string) : solution1.string != null) return false;
        if (date != null ? !date.equals(solution1.date) : solution1.date != null) return false;
        return solution != null ? solution.equals(solution1.solution) : solution1.solution == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = anInt;
        temp = Double.doubleToLongBits(aDouble);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public static void main(String[] args) {
        HashSet set = new HashSet();
        Solution sol1 = new Solution(12, null, 12.,new Date(123),null);
        Solution sol2 = new Solution(12, null, 12.,new Date(123),null);
        set.add(sol1);
        set.add(sol2);
        System.out.println(set.contains(sol2));
        System.out.println(set.size());

        System.out.println(sol1.equals(sol2));
    }
}
