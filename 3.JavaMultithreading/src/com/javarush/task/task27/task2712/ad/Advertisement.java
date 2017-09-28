package com.javarush.task.task27.task2712.ad;

/**
 * Created by Станислав on 16.09.2017.
 */
public class Advertisement {
    private Object content;
    private String name;
    private long initialAmount;
    private int hits;
    private int duration;
    private long amountPerOneDisplaying;

    public Advertisement(Object content, String name, long initialAmount, int hits, int duration) {
        this.content = content;
        this.name = name;
        this.initialAmount = initialAmount;
        this.hits = hits;
        this.duration = duration;
        amountPerOneDisplaying = initialAmount/hits;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public long getAmountPerOneDisplaying() {
        return amountPerOneDisplaying;
    }

    public int getHits() {
        return hits;
    }

    public double getAmountPerSecond() {
        return (double)amountPerOneDisplaying / duration;
    }

    public void revalidate() throws
            UnsupportedOperationException {
        if (getHits() <= 0)
            //throw new UnsupportedOperationException();
            throw new NoVideoAvailableException();
        hits--;
    }
}
