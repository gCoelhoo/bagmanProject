package com.ldts.bagman.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PositionTest {
    private Position position;

    @BeforeEach
    public void setupPosition() { position = new Position(2,3);}

    @Test
    public void getX() {
        Assertions.assertEquals(2, position.getX());
    }

    @Test
    public void getY() {
        Assertions.assertEquals(3, position.getY());
    }

    @Test
    public void setX() {
        position.setX(5);
        Assertions.assertEquals(5, position.getX());
    }

    @Test
    public void setY() {
        position.setY(5);
        Assertions.assertEquals(5, position.getY());
    }

    @Test
    public void moveUp() {
        Assertions.assertEquals(2, position.moveUP().getX());
        Assertions.assertEquals(2, position.moveUP().getY());
    }

    @Test
    public void moveDown() {
        Assertions.assertEquals(2, position.moveDOWN().getX());
        Assertions.assertEquals(4, position.moveDOWN().getY());

        int speed = 2;
        Assertions.assertEquals(2, position.moveDOWN(speed).getX());
        Assertions.assertEquals(5, position.moveDOWN(speed).getY());
    }

    @Test
    public void moveLeft() {
        Assertions.assertEquals(1, position.moveLEFT().getX());
        Assertions.assertEquals(3, position.moveLEFT().getY());
    }

    @Test
    public void moveRight() {
        Assertions.assertEquals(3, position.moveRIGHT().getX());
        Assertions.assertEquals(3, position.moveRIGHT().getY());
    }

    @Test
    public void positionRandomizer() {

    }

    @Test
    public void equals() {
        Position position2 = new Position(2,3);
        Assertions.assertTrue(position.equals(position2));
    }

    @Test
    public void distance() {
        Position position2 = new Position(6,6);
        Assertions.assertEquals(5, position.distance(position2));
    }
}
