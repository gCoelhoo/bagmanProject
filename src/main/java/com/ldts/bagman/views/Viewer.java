package com.ldts.bagman.views;

import com.googlecode.lanterna.SGR;
import com.ldts.bagman.gui.GUI;
import com.ldts.bagman.models.Elements.Element;
import com.ldts.bagman.models.MapModels.Map;
import com.ldts.bagman.models.Menu.Menu;
import com.ldts.bagman.models.Position;
import com.ldts.bagman.views.Menu.HelpView;
import com.ldts.bagman.views.Menu.MenuView;

import java.io.IOException;
import java.util.List;

public class Viewer {
    private final Map gameMap;
    private final Menu menu;

    public Viewer(Map gameMap, Menu menu){
        this.gameMap = gameMap;
        this.menu = menu;
    }

    public void draw(GUI lanternaGUI) throws IOException {
        lanternaGUI.clear();

        if(gameMap != null) {
            drawInterfaceElements(lanternaGUI);
            drawModel(lanternaGUI);
        }
        if(menu != null && menu.getPreviousMenu() == null)
            drawMenu(lanternaGUI);
        else if(menu != null && menu.getPreviousMenu() != null)
            drawHelp(lanternaGUI);

        lanternaGUI.refresh();
    }

    protected void drawMenu(GUI gui){
        new MenuView(menu, gui);
    }
    protected void drawHelp(GUI gui) throws IOException { new HelpView(gui); }
    protected void drawModel(GUI lanternaGUI){
        drawList(gameMap.getObjects(), new ObjectView(), lanternaGUI);
        drawList(gameMap.getWalls(), new WallView(), lanternaGUI);
        drawList(gameMap.getLadders(), new LadderView(), lanternaGUI);
        drawObject(gameMap.getPlayer(), new PlayerView(), lanternaGUI);
        drawList(gameMap.getPolicemen(), new PolicemenView(), lanternaGUI);
    }

    private <T extends Element> void drawList(List<T> objects, ElementViewer<T> viewer, GUI lanternaGUI){
        for(T object : objects)
            if (0 <= object.getPosition().getX() && object.getPosition().getX() <= 35) {
                drawObject(object, viewer, lanternaGUI);
            }
    }
    private <T extends Element> void drawObject(T object, ElementViewer<T> viewer, GUI lanternaGUI){
        if (0 <= object.getPosition().getX() && object.getPosition().getX() <= 35) {
            viewer.draw(object, lanternaGUI);
        }
    }
    private void drawInterfaceElements(GUI gui){
        int lives = gameMap.getPlayer().getLives();
        int points = gameMap.getPlayer().getPoints();

        gui.drawText(new Position(0,0), "Lives:", "#B22222", "#000000", SGR.BOLD);
        for(int i = 0; i < lives; ++i)
            gui.drawElementInterface(new Position(6+i, 0), 0x2764,
                    "#FF0000");

        gui.drawText(new Position(0, 1), "Points:", "#B22222", "#000000", SGR.BOLD);
        gui.drawText(new Position(8,1), String.valueOf(points), "#B22222", "#000000", SGR.BOLD);

        gui.drawText(new Position(31,0), "Continue", "#B22222", "#000000", SGR.BOLD);
        gui.drawText(new Position(37,1), "->", "#B22222", "#000000", SGR.BOLD);
    }

    public Map getGameMap() {
        return gameMap;
    }
}
