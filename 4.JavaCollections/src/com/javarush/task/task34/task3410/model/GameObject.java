package com.javarush.task.task34.task3410.model;

import java.awt.*;

/**
 * Created by Станислав on 14.09.2017.
 */
public abstract class GameObject {
    private int x;
    private int y;
    private int width;
    private int height;

    public GameObject(int x, int y) {
        this(x, y, Model.FIELD_CELL_SIZE, Model.FIELD_CELL_SIZE);
    }

    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void draw(Graphics graphics);

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
