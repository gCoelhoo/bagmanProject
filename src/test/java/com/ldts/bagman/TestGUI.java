package com.ldts.bagman;

import com.ldts.bagman.controllers.GameMapController;
import com.ldts.bagman.gui.GUI;
import com.ldts.bagman.gui.LanternaGUI;
import com.ldts.bagman.states.GameState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class TestGUI {
    @Test
    public void consoleQuitTest() throws IOException {
        Game game = Mockito.mock(Game.class);
        LanternaGUI gui = Mockito.mock(LanternaGUI.class);
        GameState gameState = Mockito.mock(GameState.class);
        GameMapController mapController = Mockito.mock(GameMapController.class);

        gameState.update(game, gui, 10);
        Mockito.when(gui.getNextAction()).thenReturn(GUI.ACTIONS.QUIT);
        mapController.update(game, gui.getNextAction(), 10);

        Mockito.verify(gui, Mockito.times(1)).getNextAction();
        Assertions.assertNull(game.getState());
    }
}
