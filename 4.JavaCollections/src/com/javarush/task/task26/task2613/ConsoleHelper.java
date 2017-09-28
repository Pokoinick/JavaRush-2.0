package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

/**
 * Created by Станислав on 10.09.2017.
 */
public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common_en");

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        try {
            String answer = bis.readLine();
            if (answer.equalsIgnoreCase("exit")) {
                throw new InterruptOperationException();
            }
            return answer;
        } catch (IOException e) {
            return "";
        }
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        writeMessage(res.getString("choose.currency.code"));
        while (true) {
            String code = readString();
            if (code.length() == 3)
                return code.toUpperCase();
            writeMessage(res.getString("invalid.data"));
        }
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
        while (true) {
            String[] digits = readString().split(" ");
            try {
                if (digits.length == 2 && Integer.parseInt(digits[0]) > 0 && Integer.parseInt(digits[1]) > 0)
                    return digits;
            } catch (Exception e) {}
            writeMessage(res.getString("invalid.data"));
        }
    }

    public static Operation askOperation() throws InterruptOperationException {
        writeMessage(res.getString("choose.operation") +
                ": 1 - " + res.getString("operation.INFO") +
                ", 2 - " + res.getString("operation.DEPOSIT") +
                ", 3 - " + res.getString("operation.WITHDRAW") +
                ", 4 - " + res.getString("operation.EXIT"));
        String operationNumber = readString();
        while (true) {
            try {
                return Operation.getAllowableOperationByOrdinal(Integer.parseInt(operationNumber));
            } catch (Exception e) {
                writeMessage(res.getString("invalid.data"));
                operationNumber = readString();
            }
        }
    }

    public static void printExitMessage() {
        writeMessage("the.end");
    }

}
