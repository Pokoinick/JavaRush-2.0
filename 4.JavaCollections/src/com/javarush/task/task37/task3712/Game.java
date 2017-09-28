package com.javarush.task.task37.task3712;

/**
 * Created by Станислав on 12.08.2017.
 */
public abstract class Game {
    public void run() {
        prepareForTheGame();
        playGame();
        congratulateWinner();
    }

    public abstract void prepareForTheGame();

    public abstract void playGame();

    public abstract void congratulateWinner();
}
