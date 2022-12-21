package com.ldts.bagman.models.Menu;

public class Menu {
    private int pointer;
    private Menu previousMenu;

    public Menu() {
        this(null);
        this.pointer = 0;
    }

    public Menu(Menu previousMenu){
        this.previousMenu = previousMenu;
    }

    public void movePointerUp(){
        if(this.pointer > 0)
            this.pointer -= 1;
    }
    public void movePointerDown(){
        if(this.pointer < 2)
            this.pointer += 1;
    }

    public void setPointer(int pointer) {
        this.pointer = pointer;
    }
    public int getPointer(){ return this.pointer; }
    public Menu getPreviousMenu() {
        return previousMenu;
    }
}