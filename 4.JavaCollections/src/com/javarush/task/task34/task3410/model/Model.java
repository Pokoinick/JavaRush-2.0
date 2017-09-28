package com.javarush.task.task34.task3410.model;

import com.javarush.task.task34.task3410.controller.EventListener;

import java.nio.file.Paths;

/**
 * Created by Станислав on 14.09.2017.
 */
public class Model {
    public static final int FIELD_CELL_SIZE = 20;
    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private LevelLoader levelLoader = new LevelLoader(Paths.get("C:\\Users\\Станислав\\Desktop\\Java\\JavaRush2.0\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task34\\task3410\\res\\levels.txt"));

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        currentLevel += 1;
        restartLevel(currentLevel);
    }

    public void move(Direction direction) {
        Player player = gameObjects.getPlayer();
        if (checkWallCollision(player, direction)) {
            return;
        }
        if (checkBoxCollisionAndMoveIfAvaliable(direction)) {
            return;
        }
        if (direction.equals(Direction.LEFT))
            player.move(- Model.FIELD_CELL_SIZE, 0);

        if (direction.equals(Direction.RIGHT))
            player.move(Model.FIELD_CELL_SIZE, 0);

        if (direction.equals(Direction.UP))
            player.move(0, -Model.FIELD_CELL_SIZE);

        if (direction.equals(Direction.DOWN))
            player.move(0, Model.FIELD_CELL_SIZE);
        checkCompletion();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        for (Wall w : gameObjects.getWalls()) {
            if(gameObject.isCollision(w, direction)){
                return true;
            }
        }

        return false;
    }

    public boolean checkBoxCollisionAndMoveIfAvaliable(Direction direction) {

        Player player = gameObjects.getPlayer();

        // найдем во что уперся игрок
        GameObject  stoped = null;
        for (GameObject gameObject: gameObjects.getAll()){
            if (!(gameObject instanceof Player)&&!(gameObject instanceof Home) && player.isCollision(gameObject, direction)){
                stoped = gameObject;
            }
        }
        //свободное место или дом
        if ((stoped == null)){
            return false;
        }
        if (stoped instanceof Box){
            Box stopedBox = (Box)stoped;
            if (checkWallCollision(stopedBox, direction)){
                return true;
            }
            for (Box box : gameObjects.getBoxes()){
                if(stopedBox.isCollision(box, direction)){
                    return true;
                }
            }
            switch (direction)
            {
                case LEFT:
                    stopedBox.move(-Model.FIELD_CELL_SIZE, 0);
                    break;
                case RIGHT:
                    stopedBox.move(Model.FIELD_CELL_SIZE, 0);
                    break;
                case UP:
                    stopedBox.move(0, -Model.FIELD_CELL_SIZE);
                    break;
                case DOWN:
                    stopedBox.move(0, Model.FIELD_CELL_SIZE);
            }
        }
        return false;

    }

    public void checkCompletion() {
        boolean isOnPlace = false;
        for (Box b : gameObjects.getBoxes()) {
            for (Home h : gameObjects.getHomes()) {
                if (b.getY() == h.getY() && b.getX() == h.getX()) {
                    isOnPlace = true;
                    break;
                }
                isOnPlace = false;
            }
        }
        if (isOnPlace) {
            eventListener.levelCompleted(currentLevel);
        }
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }
}
