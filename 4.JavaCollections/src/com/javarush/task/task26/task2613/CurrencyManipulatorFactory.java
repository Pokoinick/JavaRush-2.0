package com.javarush.task.task26.task2613;

import java.util.*;

/**
 * Created by Станислав on 13.09.2017.
 */
public class CurrencyManipulatorFactory {
    private static final Map<String, CurrencyManipulator> map = new HashMap<>();

    private CurrencyManipulatorFactory(){}

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
        String code = currencyCode.toUpperCase();
        if (map.containsKey(code)) {
            return map.get(code);
        }
        CurrencyManipulator currencyManipulator = new CurrencyManipulator(code);
        map.put(currencyCode, currencyManipulator);
        return currencyManipulator;
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators() {
        List<CurrencyManipulator> manipulators = new ArrayList<>();
        for (Map.Entry<String, CurrencyManipulator> entry : map.entrySet()) {
            manipulators.add(entry.getValue());
        }

        return manipulators;
    }
}
