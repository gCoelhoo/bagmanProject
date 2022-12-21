package com.ldts.bagman.models.Menu;

public class Help extends Menu{
    private final int pointer;
    private final Menu previousMenu;

    public Help(Menu menu) {
        this.previousMenu = menu;
        this.pointer = 3;
    }

    @Override
    public Menu getPreviousMenu() {
        return this.previousMenu;
    }

    @Override
    public int getPointer() {
        return this.pointer;
    }
}