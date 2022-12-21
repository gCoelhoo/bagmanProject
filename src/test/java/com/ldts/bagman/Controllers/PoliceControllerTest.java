package com.ldts.bagman.Controllers;

import com.ldts.bagman.Game;
import com.ldts.bagman.controllers.PolicemenController;
import com.ldts.bagman.gui.GUI;
import com.ldts.bagman.models.Elements.Element;
import com.ldts.bagman.models.Elements.Player;
import com.ldts.bagman.models.Elements.Policeman;
import com.ldts.bagman.models.MapModels.Map;
import com.ldts.bagman.models.MapModels.MapCreator;
import com.ldts.bagman.models.Position;
import com.ldts.bagman.sounds.SoundEffect;
import com.ldts.bagman.states.GameState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;

public class PoliceControllerTest {
    private Policeman policeman;
    private Position initialPos;

    @BeforeEach
    void setup(){
        this.policeman = new Policeman(0,0);
        this.initialPos = new Position(2,2);
    }

    @Test
    public void movePositionTest(){
        Position newPosRight = new Position(3,2);

        policeman.setPosition(newPosRight);

        Assertions.assertEquals(3, policeman.getPosition().getX());
        Assertions.assertEquals(2, policeman.getPosition().getY());
    }

    @Test
    public void moveTest() throws IOException {
        Game game = Mockito.mock(Game.class);
        Map map = new Map(20,20);
        PolicemenController controller = new PolicemenController();
        map.setPolicemen(List.of(policeman));
        Mockito.when(game.getState()).thenReturn(new GameState(new MapCreator().createMap()));

        controller.update(game, GUI.ACTIONS.NONE, 501);

        Assertions.assertNotEquals(initialPos, policeman.getPosition());
    }
}
