package com.javarush.task.task22.task2211;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* 
Смена кодировки
*/
public class Solution {
    static String win1251TestString = "РќР°СЂСѓС€РµРЅРёРµ РєРѕРґРёСЂРѕРІРєРё РєРѕРЅСЃРѕР»Рё?"; //only for your testing

    public static void main(String[] args) throws IOException {
        byte[] bytesCP1251 = null;
        try (FileInputStream fis = new FileInputStream(args[0]);
             FileOutputStream fos = new FileOutputStream(args[1])) {

            bytesCP1251 = new byte[fis.available()];

            int count = fis.read(bytesCP1251);

            String input = new String(bytesCP1251, "UTF-8");
            bytesCP1251 = input.getBytes("Windows-1251");

            fos.write(bytesCP1251);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
