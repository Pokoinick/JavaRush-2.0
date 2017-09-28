package com.javarush.task.task22.task2208;

import java.util.HashMap;
import java.util.Map;

/* 
Формируем WHERE
*/
public class Solution {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("name", "Ivanov");
        map.put("country", "Ukraine");
        map.put("city", "Kiev");
        map.put("age", null);

        System.out.println(getQuery(map));
    }
    public static String getQuery(Map<String, String> params) {
        StringBuilder query = new StringBuilder();
        for (Map.Entry<String, String> etry: params.entrySet()) {
            if(etry.getValue() != null) {
                if(!query.toString().equals(""))
                    query.append(" and ");
                query.append(String.format("%s = '%s'",etry.getKey(),etry.getValue()));
            }
        }
        return query.toString();
    }
}
