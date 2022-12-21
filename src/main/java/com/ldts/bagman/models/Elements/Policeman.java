package com.ldts.bagman.models.Elements;

public class Policeman extends Character {
    private Element.DIRECTION current_direction;

    public Policeman(int x, int y) {
        super(x, y);
    }

    public DIRECTION getCurrent_direction() {
        return current_direction;
    }

    public void setCurrent_direction(DIRECTION current_direction) {
        this.current_direction = current_direction;
    }
}