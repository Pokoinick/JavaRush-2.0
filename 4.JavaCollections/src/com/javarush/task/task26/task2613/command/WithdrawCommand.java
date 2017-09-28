package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by Станислав on 13.09.2017.
 */
class WithdrawCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw_en");
    @Override
    public void execute() throws InterruptOperationException {

        ConsoleHelper.writeMessage(res.getString("before"));
        String code = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
        ConsoleHelper.writeMessage(res.getString("specify.amount"));
        while (true) {
            try {
                int amount = Integer.parseInt(ConsoleHelper.readString());
                if (amount < 0) {
                    ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                    continue;
                }
                if (!currencyManipulator.isAmountAvailable(amount)) {
                    ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                    continue;
                }
                Map<Integer, Integer> withdraw = currencyManipulator.withdrawAmount(amount);

                for (Map.Entry<Integer, Integer> map : withdraw.entrySet()) {
                    ConsoleHelper.writeMessage("\t" + map.getKey() + " - " + map.getValue());
                }
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"), amount, code));
                break;


            } catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
                break;
            } catch (Exception e) {
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
            }
        }

    }
}
