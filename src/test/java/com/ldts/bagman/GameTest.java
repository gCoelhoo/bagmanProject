package com.ldts.bagman;

import com.ldts.bagman.gui.LanternaGUI;
import com.ldts.bagman.models.MapModels.Map;
import com.ldts.bagman.states.GameState;
import com.ldts.bagman.states.State;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;

public class GameTest {
    private Game game;
    private State state = null;
    private String[] args = new String[0];
    private Map stubMap = Mockito.mock(Map.class);
    private State stubState = Mockito.mock(State.class);
    private LanternaGUI stubLanternaGUI = Mockito.mock(LanternaGUI.class);
    @BeforeEach
    public void setup() {
        game = new Game(stubState, stubLanternaGUI);
    }
    @Test
    public void state() {
        Assertions.assertEquals(stubState, game.getState());
        game.setState(state);
        Assertions.assertEquals(null, game.getState());
    }

    @Test
    public void run() throws IOException {
        game.setState(state);
        game.run();
        Mockito.verify(stubLanternaGUI, Mockito.times(1)).close();
    }
}
