package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/* 
Что внутри папки?
*/
public class Solution {
    public static int foldersCounts;
    public static int filesCount;
    public static long totalSize;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String mainFolder = reader.readLine();
            Path mainPath = Paths.get(mainFolder);
            if (!Files.isDirectory(mainPath)) {
                System.out.println(mainFolder + " - не папка");
                return;
            }

            Files.walkFileTree(mainPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    if (Files.isDirectory(dir))
                        foldersCounts++;
                    return super.preVisitDirectory(dir, attrs);
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (Files.isDirectory(file))
                        foldersCounts++;
                    if (Files.isRegularFile(file)) {
                        filesCount++;
                        totalSize += Files.size(file);
                    }
                    return super.visitFile(file, attrs);
                }
            });
            System.out.println("Всего папок - " + (foldersCounts-1));
            System.out.println("Всего файлов - " + filesCount);
            System.out.println("Общий размер - " + totalSize);
        }
    }
}
