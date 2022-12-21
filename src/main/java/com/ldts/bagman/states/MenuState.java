package com.ldts.bagman.states;

import com.ldts.bagman.controllers.Controller;
import com.ldts.bagman.controllers.Menu.MenuController;
import com.ldts.bagman.models.MapModels.Map;
import com.ldts.bagman.models.Menu.Menu;
import com.ldts.bagman.views.Viewer;

public class MenuState extends State{

    public MenuState(Menu menu) {
        super(null, menu);
    }

    @Override
    protected Controller getController() {
        return new MenuController();
    }

    @Override
    protected Viewer getViewer() {
        return new Viewer(null, getMenu());
    }
}