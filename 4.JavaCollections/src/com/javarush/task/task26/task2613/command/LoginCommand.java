package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

/**
 * Created by Станислав on 13.09.2017.
 */
public class LoginCommand implements Command {
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login_en");
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        while (true) {
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            String card = ConsoleHelper.readString();
            String pin = ConsoleHelper.readString();
            if (card.length() == 12 && pin.length() == 4) {
                if (validCreditCards.containsKey(card) && validCreditCards.getString(card).equals(pin)) {
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"), card));
                    break;
                }
                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), card));
            }
            ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
            ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
        }
    }
}
