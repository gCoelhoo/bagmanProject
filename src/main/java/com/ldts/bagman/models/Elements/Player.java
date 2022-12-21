package com.ldts.bagman.models.Elements;

public class Player extends Character {
    private Object object;
    private boolean isCarryingObject;
    private int lives;
    private int points;
    private DIRECTION lastLR;

    public Player(int x, int y) {
        super(x, y);
        isCarryingObject = false;
        this.lives = 3;
        points = 0;
        lastLR = DIRECTION.RIGHT;
    }

    public Object dropObject() {
        isCarryingObject = false;
        object.setPosition(this.getPosition());
        Object o = object;
        object = null;
        return o;
    }

    public boolean isCarryingObject() {
        return isCarryingObject;
    }

    public void setCarryingObject(Object object) {
        isCarryingObject = true;
        this.object = object;
    }

    public Object getObject() {
        return object;
    }

    public int getLives() {
        return lives;
    }

    public void setDamage() {
        this.lives--;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points += points;
    }

    public DIRECTION getLastLR() {
        return lastLR;
    }

    public void setLastLR(DIRECTION lastLR) {
        this.lastLR = lastLR;
    }
}