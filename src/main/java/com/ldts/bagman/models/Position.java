package com.ldts.bagman.models;

import com.ldts.bagman.models.Elements.Element;
import com.ldts.bagman.models.Elements.Player;
import com.ldts.bagman.models.Elements.Policeman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Position {
    private int x;
    private int y;

    public Position(int column, int row) {
        x = column;
        y = row;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Position moveUP(){
        return new Position(this.x, this.y -1);
    }
    public Position moveDOWN(){
        return new Position(this.x, this.y +1);
    }
    public Position moveDOWN(int speed){
        return new Position(this.x, this.y +speed);
    }
    public Position moveLEFT(){
        return new Position(this.x-1, this.y);
    }
    public Position moveRIGHT(){
        return new Position(this.x+1, this.y);
    }


    /*Something wrong happening here. The FLAG does not seem to be updated*/
    public Element.DIRECTION posRandomizer(Element.FLAG moveFlag, boolean can_move_left, boolean can_move_right, boolean can_move_up, boolean can_move_down, Policeman policeman, Player player){
        Random random = new Random();
        List<Element.DIRECTION> available = new ArrayList<>();
        if (can_move_left) { available.add(Element.DIRECTION.LEFT); }
        if (can_move_right) { available.add(Element.DIRECTION.RIGHT); }
        if (can_move_up) { available.add(Element.DIRECTION.UP); }
        if (can_move_down) { available.add(Element.DIRECTION.DOWN); }
        if (moveFlag == Element.FLAG.ONAIR) {
            return Element.DIRECTION.DOWN;
        }
        if (moveFlag == Element.FLAG.ONGROUND) {
            available.remove(Element.DIRECTION.DOWN);
            available.remove(Element.DIRECTION.UP);
        }
        if (player.getPosition().getX() == policeman.getPosition().getX()) {
            if ((player.getPosition().getY() < policeman.getPosition().getY()) && (available.contains(Element.DIRECTION.UP))) {
                return Element.DIRECTION.UP;
            }
            else if (available.contains(Element.DIRECTION.DOWN)) {
                return Element.DIRECTION.DOWN;
            }
        }
        if (player.getPosition().getY() == policeman.getPosition().getY()) {
            if ((player.getPosition().getX() < policeman.getPosition().getX()) && (available.contains(Element.DIRECTION.LEFT))) {
                return Element.DIRECTION.LEFT;
            }
            else if (available.contains(Element.DIRECTION.RIGHT)) {
                return Element.DIRECTION.RIGHT;
            }
        }
        if (available.contains(policeman.getCurrent_direction()) && (random.nextInt(100) < 95)) {
            return policeman.getCurrent_direction();
        }
        int i = random.nextInt(available.size());
        return available.get(i);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null) return false;

        if (getClass() != o.getClass()) return false;

        Position p = (Position) o;
        return x == p.getX() && y == p.getY();
    }
    public float distance(Position p) {
        return (float) Math.sqrt((this.x - p.getX())*(this.x - p.getX()) + (this.y - p.getY())*(this.y - p.getY()));
    }
}
