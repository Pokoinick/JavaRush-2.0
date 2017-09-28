package com.javarush.task.task39.task3906;

public class LightBulb implements Switchable {
    private boolean on = false;

    public boolean isOn() {
        return on;
    }

    public void turnOn() {
        System.out.println("The light is shining!");
        on = true;
    }

    public void turnOff() {
        System.out.println("The light has been turned off!");
        on = false;
    }
}
