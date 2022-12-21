package com.ldts.bagman.controllers;

import com.ldts.bagman.Game;
import com.ldts.bagman.gui.GUI;

public abstract class Controller {
    public abstract void update(Game game, GUI.ACTIONS action, long time);
}
