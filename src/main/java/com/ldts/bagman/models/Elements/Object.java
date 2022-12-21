package com.ldts.bagman.models.Elements;

public abstract class Object extends Element {

    public enum TYPE {WHEELBARROW, PICKAXE, COINBAG}
    private boolean isBeingCarried;
    public Object(int x, int y) {
        super(x, y);
        isBeingCarried = false;
    }
    public void setBeingCarried(boolean beingCarried) {
        isBeingCarried = beingCarried;
    }
    public boolean isBeingCarried() { return isBeingCarried; }
    public abstract TYPE type();
}