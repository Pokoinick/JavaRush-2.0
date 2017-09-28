package com.javarush.task.task25.task2506;

/**
 * Created by Станислав on 16.05.2017.
 */
public class LoggingStateThread extends Thread {

    private Thread thread;
    LoggingStateThread(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        State state = thread.getState();
        System.out.println(State.NEW);
        while (!state.equals(State.TERMINATED)){
            if(!(state.equals(thread.getState()))) {
                state = thread.getState();
                System.out.println(state);
            }
        }
    }
}
