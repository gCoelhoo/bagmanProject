package com.ldts.bagman.states;

import com.ldts.bagman.controllers.Controller;
import com.ldts.bagman.controllers.GameMapController;
import com.ldts.bagman.models.MapModels.Map;
import com.ldts.bagman.views.Viewer;

public class GameState extends State {
    public GameState(Map gameMap){
        super(gameMap, null);
    }

    @Override
    protected Controller getController() {
        return new GameMapController();
    }

    @Override
    protected Viewer getViewer() {
        return new Viewer(getGameMap(), null);
    }
}
