package com.javarush.task.task34.task3410.model;

import java.awt.*;

/**
 * Created by Станислав on 15.09.2017.
 */
public class Wall extends CollisionObject {
    public Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.GRAY);
        graphics.fillOval(getX(), getY(), getWidth(), getHeight());
    }
}
