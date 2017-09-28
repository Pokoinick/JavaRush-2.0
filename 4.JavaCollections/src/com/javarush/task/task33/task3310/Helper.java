package com.javarush.task.task33.task3310;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by Станислав on 31.07.2017.
 */
public class Helper {
    public static String generateRandomString() {
        SecureRandom random = new SecureRandom();
        BigInteger ss =  new BigInteger(130, random);
        return ss.toString(32);
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }
}
