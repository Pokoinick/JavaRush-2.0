package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        File file = new File(root);
        List<String> result =new ArrayList<>();
        Queue<File> queue = new ArrayDeque<>();
        if (file.isDirectory())
            queue.add(file);
        else if (file.isFile())
            result.add(file.getAbsolutePath());
        while (queue.size() > 0) {
            for (File inner : queue.poll().listFiles()) {
                if (inner.isDirectory())
                    queue.add(inner);
                if (inner.isFile())
                    result.add(inner.getAbsolutePath());

            }
        }
        return result;

    }

    public static void main(String[] args) {
        
    }
}
