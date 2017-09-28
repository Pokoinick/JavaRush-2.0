package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

/**
 * Created by Станислав on 13.09.2017.
 */
interface Command  {
    void execute() throws InterruptOperationException;
}
