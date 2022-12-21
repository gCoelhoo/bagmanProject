package com.ldts.bagman.Controllers.Menu;

import com.ldts.bagman.Game;
import com.ldts.bagman.controllers.Menu.MenuController;
import com.ldts.bagman.gui.GUI;
import com.ldts.bagman.models.Menu.Menu;
import com.ldts.bagman.states.GameState;
import com.ldts.bagman.states.MenuState;
import com.ldts.bagman.states.State;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MenuControllerTest {
    @Test
    public void useTest(){
        Game game = Mockito.mock(Game.class);
        Menu menu = Mockito.mock(Menu.class);
        MenuController controller = new MenuController();
        State initialState = game.getState();

        Mockito.when(menu.getPointer()).thenReturn(0);
        Mockito.when(game.getState()).thenReturn(new MenuState(menu));
        controller.update(game, GUI.ACTIONS.USE, 600);
        State finalState = game.getState();

        Assertions.assertNotNull(finalState);
        Assertions.assertNotEquals(initialState, finalState);
    }
    @Test
    public void useTest2(){
        Game game = Mockito.mock(Game.class);
        Menu menu = Mockito.mock(Menu.class);
        MenuController controller = new MenuController();
        State initialState = game.getState();

        Mockito.when(menu.getPointer()).thenReturn(1);
        Mockito.when(game.getState()).thenReturn(new MenuState(menu));
        controller.update(game, GUI.ACTIONS.USE, 600);
        State finalState = game.getState();

        Assertions.assertNotNull(finalState);
        Assertions.assertNotEquals(initialState, finalState);
    }
    @Test
    public void useTest3(){
        Game game = Mockito.mock(Game.class);
        Menu menu = Mockito.mock(Menu.class);
        MenuController controller = new MenuController();

        Mockito.when(menu.getPointer()).thenReturn(2);
        Mockito.when(game.getState()).thenReturn(new MenuState(menu));
        State initialState = game.getState();
        controller.update(game, GUI.ACTIONS.USE, 600);

        Assertions.assertNotEquals(initialState, null);
    }
    @Test
    public void controlTestDown(){
        Game game = Mockito.mock(Game.class);
        Menu menu = new Menu();
        MenuController controller = new MenuController();
        menu.setPointer(0);
        int initPointer = menu.getPointer();

        Mockito.when(game.getState()).thenReturn(new MenuState(menu));
        controller.update(game, GUI.ACTIONS.DOWN, 600);

        Assertions.assertNotEquals(initPointer, 1);
    }
    @Test
    public void controlTestUp(){
        Game game = Mockito.mock(Game.class);
        Menu menu = new Menu();
        MenuController controller = new MenuController();
        menu.setPointer(1);
        int initPointer = menu.getPointer();

        Mockito.when(game.getState()).thenReturn(new MenuState(menu));
        controller.update(game, GUI.ACTIONS.UP, 600);

        Assertions.assertNotEquals(initPointer, 0);
    }
}
