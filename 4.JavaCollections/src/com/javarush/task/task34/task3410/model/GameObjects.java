package com.javarush.task.task34.task3410.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Станислав on 15.09.2017.
 */
public class GameObjects {
    private Set<Wall> walls;
    private Set<Box> boxes;
    private Set<Home> homes;
    private Player player;

    public GameObjects(Set<Wall> walls, Set<Box> boxes, Set<Home> homes, Player player) {
        this.walls = walls;
        this.boxes = boxes;
        this.homes = homes;
        this.player = player;
    }

    public Set<GameObject> getAll() {
        Set<GameObject> allObjects = new HashSet<>();

        for (Box b : boxes) {
            allObjects.add(b);
        }

        for (Wall w : walls) {
            allObjects.add(w);
        }

        for (Home h : homes) {
            allObjects.add(h);
        }

        allObjects.add(player);
        return allObjects;
    }

    public Set<Wall> getWalls() {
        return walls;
    }

    public Set<Box> getBoxes() {
        return boxes;
    }

    public Set<Home> getHomes() {
        return homes;
    }

    public Player getPlayer() {
        return player;
    }
}
