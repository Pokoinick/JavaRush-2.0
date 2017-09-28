package com.javarush.task.task39.task3906;

public class ElectricPowerSwitch {
    private Switchable aSwitchable;

    public ElectricPowerSwitch(Switchable aSwitchable) {
        this.aSwitchable = aSwitchable;
    }

    public void press() {
        System.out.println("Pressed the power switch.");
        if (aSwitchable.isOn()) {
            aSwitchable.turnOff();
        } else {
            aSwitchable.turnOn();
        }
    }
}
