package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Станислав on 13.09.2017.
 */
public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations = new HashMap<>();

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        if (denominations.containsKey(denomination)) {
            int amount = denominations.get(denomination) + count;
            denominations.put(denomination, amount);
        }
        else
            denominations.put(denomination, count);
    }

    public int getTotalAmount() {
        int result = 0;

        for (Map.Entry<Integer, Integer> map : denominations.entrySet()) {
            result += map.getKey() * map.getValue();
        }

        return result;
    }

    public boolean hasMoney() {
           return getTotalAmount() > 0;
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return expectedAmount <= getTotalAmount();
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        TreeMap<Integer, Integer> withdrawResult = new TreeMap<>(Comparator.reverseOrder());
        TreeMap<Integer, Integer> test = new TreeMap<>(Comparator.reverseOrder());

        int withdrawSum = expectedAmount;



            for (Map.Entry<Integer, Integer> currentAmount : denominations.entrySet()) {
                test.put(currentAmount.getKey(), currentAmount.getValue());
            }

            for (Map.Entry<Integer, Integer> currentAmount : test.entrySet()) {
                int count = 0;
                for (int i = 0; i < currentAmount.getValue(); i++) {
                    if (withdrawSum - currentAmount.getKey() < 0)
                        break;
                    if (withdrawSum - currentAmount.getKey() >= 0) {
                        withdrawSum -= currentAmount.getKey();
                        count++;
                    }
                }
                if (count > 0) {
                    withdrawResult.put(currentAmount.getKey(), count);
                }
            }
        if (withdrawSum > 0)
            throw new NotEnoughMoneyException();

        for (Map.Entry<Integer, Integer> total : withdrawResult.entrySet()) {
            denominations.put(total.getKey(), denominations.get(total.getKey()) - total.getValue());
        }
        return withdrawResult;
    }
}
