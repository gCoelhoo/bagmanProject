package com.ldts.bagman.controllers.Menu;

import com.ldts.bagman.Game;
import com.ldts.bagman.controllers.Controller;
import com.ldts.bagman.gui.GUI;
import com.ldts.bagman.models.MapModels.MapCreator;
import com.ldts.bagman.models.Menu.Help;
import com.ldts.bagman.models.Menu.Menu;
import com.ldts.bagman.states.GameState;
import com.ldts.bagman.states.MenuState;
import com.ldts.bagman.states.State;

import java.io.IOException;

public class MenuController extends Controller {
    @Override
    public void update(Game game, GUI.ACTIONS action, long time) {
        Menu menu = game.getState().getMenu();
        switch(action){
            case UP:
                menu.movePointerUp();
                break;
            case DOWN:
                menu.movePointerDown();
                break;
            case USE:
                switch (menu.getPointer()){
                    case 0:
                        game.setState(new GameState(new MapCreator().createMap()));
                        break;
                    case 1:
                        game.setState(new MenuState(new Help(menu)));
                        break;
                    case 2:
                        game.setState(null);
                        break;
                    case 3:
                        game.setState(new MenuState(new Menu()));
                        break;


                }
        }
    }
}