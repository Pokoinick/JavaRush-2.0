package com.javarush.task.task31.task3105;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        if (!Files.isRegularFile(Paths.get(args[1]))) {
            return;
        }

        Map<ZipEntry, byte[]> zipArch = new HashMap<>();
        ZipEntry replaceEntry = null;

        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(args[1]))) {


            ZipEntry oldZipEntry = zipInputStream.getNextEntry();


            while (oldZipEntry != null) {

                String compareName = Paths.get(args[0]).getFileName().toString();
                String compareName2 = oldZipEntry.getName();
                compareName2 = compareName2.replaceAll("\\\\", "/");
                if (compareName2.contains("/"))
                    compareName2 = compareName2.substring(compareName2.lastIndexOf('/')+1);
                if (compareName.equals(compareName2)) {
                    replaceEntry = new ZipEntry(oldZipEntry.getName());
                    oldZipEntry = zipInputStream.getNextEntry();
                }
                else {
                    try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

                        byte[] buffer = new byte[8 * 1024];
                        int len;
                        while ((len = zipInputStream.read(buffer)) > 0) {
                            byteArrayOutputStream.write(buffer, 0, len);
                        }

                        zipArch.put(oldZipEntry, byteArrayOutputStream.toByteArray());
                        oldZipEntry = zipInputStream.getNextEntry();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

            try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(args[1]))) {
                if (replaceEntry != null) {
                    zipOutputStream.putNextEntry(replaceEntry);
                    Files.copy(Paths.get(args[0]), zipOutputStream);
                } else {
                    zipOutputStream.putNextEntry(new ZipEntry("new/" + Paths.get(args[0]).getFileName().toString()));
                    Files.copy(Paths.get(args[0]), zipOutputStream);
                }

                for (Map.Entry<ZipEntry, byte[]> zipFile : zipArch.entrySet()) {
                    zipOutputStream.putNextEntry(zipFile.getKey());
                    zipOutputStream.write(zipFile.getValue());
                }
            }
    }
}
