package com.ldts.bagman.views.Menu;

import com.googlecode.lanterna.SGR;
import com.ldts.bagman.gui.GUI;
import com.ldts.bagman.models.Position;

public class HelpView {

    public HelpView(GUI gui) {
        drawText(gui);
        drawButton(gui);
    }

    public void drawButton(GUI gui){
        int x = gui.getWidth()-8;
        int y = gui.getHeight()-2;
        Position position = new Position(x, y);

        gui.drawText(position,
                "> Back", "#B22222", "#FFD700", SGR.BOLD);
    }
    public void drawText(GUI gui){
        int x = gui.getWidth()/2-3;
        Position initPos = new Position(x,0);

        gui.drawText(new Position(6,4),
                "The objective is to collect",
                "#B22222", "#FFFFFF", SGR.BOLD);
        gui.drawText(new Position(8,5),
                "gold to earn points.",
                "#B22222", "#FFFFFF", SGR.BOLD);
        gui.drawText(new Position(8,7),
                "You have only 3 lives.",
                "#B22222", "#FFFFFF", SGR.BOLD);
        gui.drawText(new Position(10,8),
                "Don't get caught",
                "#B22222", "#FFFFFF", SGR.BOLD);

        gui.drawText(new Position(6,10),
                "Press \"ArrowKeys\" to Walk,",
                "#B22222", "#FFFFFF", SGR.BOLD);
        gui.drawText(new Position(4,11),
                "Press \"Enter\" to Interact",
                "#B22222", "#FFFFFF", SGR.BOLD);
        gui.drawText(new Position(6,12),
                "In game, press \"Esc\" to Exit",
                "#B22222", "#FFFFFF", SGR.BOLD);

        gui.drawText(initPos, "Help", "#B22222", "#FFD700", SGR.BOLD);
    }
}