package com.ldts.bagman.models.Elements;

import com.ldts.bagman.models.Position;

public abstract class Element {
    private Position position;
    public enum FLAG {ONGROUND, ONLADDER, ONAIR}
    public enum DIRECTION {LEFT, RIGHT, UP, DOWN}

    public Element(int x, int y) {
        this.position = new Position(x, y);
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    //public abstract void draw(TextGraphics graphics) throws IOException;
}
