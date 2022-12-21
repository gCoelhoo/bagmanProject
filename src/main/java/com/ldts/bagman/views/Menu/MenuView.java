package com.ldts.bagman.views.Menu;

import com.googlecode.lanterna.SGR;
import com.ldts.bagman.gui.GUI;
import com.ldts.bagman.models.MapModels.Map;
import com.ldts.bagman.models.Menu.Menu;
import com.ldts.bagman.models.Position;
import com.ldts.bagman.views.Viewer;

public class MenuView{
    private int pointer;

    public MenuView(Menu menu, GUI gui) {
        this.pointer = menu.getPointer();
        drawer(gui);
    }

    public void drawer(GUI gui){
        int x = gui.getWidth()/2;
        int y = gui.getHeight()/2;
        switch(this.pointer){
            case 0:
                gui.drawText(new Position(x-5,y-5),
                        "> Start", "#B22222", "#FFD700", SGR.BOLD);
                gui.drawText(new Position(x-5, y-3),
                        "Help", "#B22222", "#FFD700", SGR.BOLD);
                gui.drawText(new Position(x-5,y-1),
                        "Exit", "#B22222", "#FFD700", SGR.BOLD);
                break;
            case 1:
                gui.drawText(new Position(x-5,y-5),
                        "Start", "#B22222", "#FFD700", SGR.BOLD);
                gui.drawText(new Position(x-5,y-3),
                        "> Help", "#B22222", "#FFD700", SGR.BOLD);
                gui.drawText(new Position(x-5,y-1),
                        "Exit", "#B22222", "#FFD700", SGR.BOLD);
                break;
            case 2:
                gui.drawText(new Position(x-5,y-5),
                        "Start", "#B22222", "#FFD700", SGR.BOLD);
                gui.drawText(new Position(x-5,y-3),
                        "Help", "#B22222", "#FFD700", SGR.BOLD);
                gui.drawText(new Position(x-5,y-1),
                        "> Exit", "#B22222", "#FFD700", SGR.BOLD);
                break;
        }
    }
}