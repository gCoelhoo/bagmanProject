package com.ldts.bagman.states;

import com.ldts.bagman.Game;
import com.ldts.bagman.controllers.Controller;
import com.ldts.bagman.gui.GUI;
import com.ldts.bagman.models.MapModels.Map;
import com.ldts.bagman.models.Menu.Menu;
import com.ldts.bagman.views.Viewer;

import java.io.IOException;

public abstract class State {
    private final Controller controller;
    private final Viewer viewer;
    private final Map gameMap;
    private final Menu menu;

    public State(Map gameMap, Menu menu) {
        this.gameMap = gameMap;
        this.menu = menu;
        this.controller = getController();
        this.viewer = getViewer();
    }

    protected abstract Controller getController();
    protected abstract Viewer getViewer();

    public void update(Game game, GUI gui, long time) throws IOException {
        GUI.ACTIONS action = gui.getNextAction();

        controller.update(game, action, time);
        viewer.draw(gui);
    }

    public Map getGameMap() {
        return gameMap;
    }
    public Menu getMenu() {
        return menu;
    }
}
