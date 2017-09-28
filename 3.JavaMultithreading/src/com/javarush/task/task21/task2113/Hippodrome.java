package com.javarush.task.task21.task2113;

import java.util.*;
/**
 * Created by Станислав on 21.04.2017.
 */
public class Hippodrome {

    private static List<Horse> horses = new ArrayList<>();
    static Hippodrome game;

    public Hippodrome(List<Horse> list) {
        horses = list;
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public void run(){
        for (int i = 0; i < 100; i++) {
            move();
            print();
            try {
                Thread.sleep(200);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void move(){
        for (Horse horse: horses) {
            horse.move();
        }

    }

    public void print() {
        for (Horse horse : horses) {
            horse.print();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    public Horse getWinner(){
        Horse winner = null;
        for (Horse horse : horses) {
            if(winner == null)
                winner = horse;
            else if (winner.getDistance()< horse.getDistance())
                winner = horse;

        }
        return winner;
    }

    public void printWinner(){
        System.out.println("Winner is " + getWinner().getName() + "!");

    }

    public static void main(String[] args) {

        Horse horse1 = new Horse("Bmw", 3,0);
        Horse horse2 = new Horse("Toyota", 3,0);
        Horse horse3 = new Horse("Jeep", 3,0);

        horses.add(horse1);
        horses.add(horse2);
        horses.add(horse3);

        game = new Hippodrome(horses);

        game.run();
        game.printWinner();
    }
}
