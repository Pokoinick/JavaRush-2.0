package com.javarush.task.task26.task2613;

/**
 * Created by Станислав on 10.09.2017.
 */
public enum Operation {
    LOGIN,
    INFO,
    DEPOSIT,
    WITHDRAW,
    EXIT;

    public static Operation getAllowableOperationByOrdinal(Integer i) throws IllegalArgumentException {
        if (i < 1 || 4 < i)
            throw new IllegalArgumentException();
        return Operation.values()[i];
    }
}
