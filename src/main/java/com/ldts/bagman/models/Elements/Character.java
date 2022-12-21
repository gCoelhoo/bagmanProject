package com.ldts.bagman.models.Elements;

import com.ldts.bagman.models.Position;

public abstract class Character extends Element{
    private int ko_timer;
    private boolean ko;
    private Position initialPos;

    public Character(int x, int y) {
        super(x, y);
        ko = false;
        this.initialPos = new Position(x, y);
    }

    public boolean isKO() { return ko; }
    public void setKo(boolean ko) {
        ko_timer = 0;
        this.ko = ko;
    }

    public int getKo_timer() {
        return ko_timer;
    }
    public void setKo_timer(int ko_timer) {
        this.ko_timer = ko_timer;
    }
    public void setInitialPos(Position pos) { initialPos = pos; }
    public Position getInitialPos() {
        return initialPos;
    }
    public void returnToInitialPos(){
        this.setPosition(initialPos);
    }
}