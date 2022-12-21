package com.ldts.bagman.models.Menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MenuTest {
    private Menu menu;

    @BeforeEach
    public void setup() {
        menu = new Menu();
    }

    @Test
    public void getPointer(){
        Assertions.assertEquals(0, menu.getPointer());
    }

    @Test
    public void setPointer() {
        menu.setPointer(3);
        Assertions.assertEquals(3, menu.getPointer());
    }

    @Test
    public void movePointerUp() {
        menu.movePointerUp();
        Assertions.assertEquals(0, menu.getPointer());

        menu.setPointer(3);
        menu.movePointerUp();
        Assertions.assertEquals(2, menu.getPointer());
    }

    @Test
    public void movePointerDown() {
        menu.movePointerDown();
        Assertions.assertEquals(1, menu.getPointer());

        menu.setPointer(3);
        menu.movePointerDown();
        Assertions.assertEquals(3, menu.getPointer());
    }

    @Test
    public void getPreviousMenu() {
        Menu menu2 = new Menu(menu);
        Assertions.assertEquals(menu, menu2.getPreviousMenu());
    }
}
