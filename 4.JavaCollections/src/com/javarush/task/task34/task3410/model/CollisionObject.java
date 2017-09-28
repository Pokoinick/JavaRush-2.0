package com.javarush.task.task34.task3410.model;


/**
 * Created by Станислав on 14.09.2017.
 */
public abstract class CollisionObject extends GameObject {

    public CollisionObject(int x, int y) {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction) {
        if (direction.equals(Direction.LEFT))
            return (this.getX() - Model.FIELD_CELL_SIZE) == gameObject.getX() && this.getY() == gameObject.getY();

        if (direction.equals(Direction.RIGHT))
            return (this.getX() + Model.FIELD_CELL_SIZE) == gameObject.getX() && this.getY() == gameObject.getY();

        if (direction.equals(Direction.UP))
            return (this.getY() - Model.FIELD_CELL_SIZE) == gameObject.getY() && this.getX() == gameObject.getX();

        return direction.equals(Direction.DOWN)
                && (this.getY() + Model.FIELD_CELL_SIZE) == gameObject.getY()
                && this.getX() == gameObject.getX();
    }
}
