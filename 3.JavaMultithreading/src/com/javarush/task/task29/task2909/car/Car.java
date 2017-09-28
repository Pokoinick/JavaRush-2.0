package com.javarush.task.task29.task2909.car;

import java.util.Date;

public abstract class Car {
    static public final int TRUCK = 0;
    static public final int SEDAN = 1;
    static public final int CABRIOLET = 2;

    final int MAX_TRUCK_SPEED = 80;
    final int MAX_SEDAN_SPEED = 120;
    final int MAX_CABRIOLET_SPEED = 90;

    double fuel;

    public double summerFuelConsumption;
    public double winterFuelConsumption;
    public double winterWarmingUp;

    private int type;

    private boolean driverAvailable;
    private int numberOfPassengers;

    protected Car(int type, int numberOfPassengers) {
        this.type = type;
        this.numberOfPassengers = numberOfPassengers;
    }

    public static Car create(int type, int numberOfPassengers){
        if(type == TRUCK)
            return new Truck(numberOfPassengers);
        if(type == SEDAN)
            return new Sedan(numberOfPassengers);
        else
            return new Cabriolet(numberOfPassengers);
    }

    public boolean isSummer(Date date, Date summerStart, Date summerEnd){
            return (summerStart.getTime() < date.getTime() && date.getTime() < summerEnd.getTime());
    }

    public double getWinterConsumption(int lenght){
        return winterWarmingUp+(winterFuelConsumption*lenght);
    }

    public double getSummerConsumption(int lenght){
        return summerFuelConsumption*lenght;
    }

    private boolean canPassengersBeTransferred(){
        return driverAvailable && fuel>0.;

    }

    public void fill(double numberOfLiters) throws Exception{
        if (numberOfLiters < 0)
            throw new Exception();
        fuel += numberOfLiters;
    }

    public double getTripConsumption(Date date, int length, Date SummerStart, Date SummerEnd) {
        if (!isSummer(date,SummerStart,SummerEnd)) {
            return getWinterConsumption(length);
        } else
            return getSummerConsumption(length);
    }

    public int getNumberOfPassengersCanBeTransferred() {
        if (!canPassengersBeTransferred())
            return 0;
        else
            return numberOfPassengers;
    }

    public boolean isDriverAvailable() {
        return driverAvailable;
    }

    public void setDriverAvailable(boolean driverAvailable) {
        this.driverAvailable = driverAvailable;
    }

    public void startMoving() {
        fastenDriverBelt();
        if (numberOfPassengers > 0) {
            fastenPassengersBelts();
        }
    }

    public void fastenPassengersBelts() {
    }

    public void fastenDriverBelt() {
    }

    public abstract int getMaxSpeed();
}