package com.ldts.bagman.Controllers;

import com.ldts.bagman.Game;
import com.ldts.bagman.controllers.CoinBagController;
import com.ldts.bagman.controllers.WheelbarrowController;
import com.ldts.bagman.gui.GUI;
import com.ldts.bagman.models.Elements.*;
import com.ldts.bagman.models.Elements.Object;
import com.ldts.bagman.models.MapModels.Map;
import com.ldts.bagman.models.MapModels.Wall;
import com.ldts.bagman.models.Position;
import com.ldts.bagman.states.State;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class WheelbarrowControllerTest {
    private Game stubGame = Mockito.mock(Game.class);
    private State stubState = Mockito.mock(State.class);
    private Map stubMap = Mockito.mock(Map.class);
    private Player stubPlayer = Mockito.mock(Player.class);
    private WheelbarrowController controller;
    private Wheelbarrow wheelbarrow;


    @BeforeEach
    public void setupCoin() throws IOException {
        wheelbarrow = new Wheelbarrow(1, 2);
        controller = new WheelbarrowController();
    }

    @Test
    public void noGround(){
        Wall wall = new Wall(1, 4);
        wheelbarrow.setBeingCarried(false);
        List<Object> listObject = new LinkedList<Object>(Arrays.asList(wheelbarrow));
        List<Wall> listWall = new LinkedList<Wall>(Arrays.asList(wall));

        Mockito.when(stubGame.getState()).thenReturn(stubState);
        Mockito.when(stubState.getGameMap()).thenReturn(stubMap);
        Mockito.when(stubMap.getObjects()).thenReturn(listObject);
        Mockito.when(stubMap.getWalls()).thenReturn(listWall);
        Mockito.when(stubMap.getPlayer()).thenReturn(stubPlayer);

        controller.update(stubGame, GUI.ACTIONS.NONE, 501);

        Assertions.assertEquals(1, wheelbarrow.getPosition().getX());
        Assertions.assertEquals(3, wheelbarrow.getPosition().getY());
        Assertions.assertEquals(false, wheelbarrow.isBeingCarried());

        wheelbarrow.setBeingCarried(true);
        Assertions.assertEquals(true, wheelbarrow.isBeingCarried());

        wall.setPosition(new Position(1,7));
        wheelbarrow.setPosition(new Position(1,2));

        controller.update(stubGame, GUI.ACTIONS.NONE, 501);

        Mockito.verify(stubPlayer, Mockito.times(1)).dropObject();
        Assertions.assertEquals(1, wheelbarrow.getPosition().getX());
        Assertions.assertEquals(4, wheelbarrow.getPosition().getY());
        Assertions.assertEquals(false, wheelbarrow.isBeingCarried());
    }

    @Test
    public void beingCarried(){
        Wall wall = new Wall(1, 3);
        Wall wall2 = new Wall(4, 6);
        Wall wall3 = new Wall(5, 6);
        Wall wall4 = new Wall(6, 6);
        wheelbarrow.setBeingCarried(true);
        List<Object> listObject = new LinkedList<Object>(Arrays.asList(wheelbarrow));
        List<Wall> listWall = Arrays.asList(wall,wall2,wall3,wall4);
        Position position = new Position(5,5);

        Mockito.when(stubGame.getState()).thenReturn(stubState);
        Mockito.when(stubState.getGameMap()).thenReturn(stubMap);
        Mockito.when(stubMap.getObjects()).thenReturn(listObject);
        Mockito.when(stubMap.getWalls()).thenReturn(listWall);
        Mockito.when(stubMap.getPlayer()).thenReturn(stubPlayer);
        Mockito.when(stubPlayer.getLastLR()).thenReturn(Element.DIRECTION.RIGHT);
        Mockito.when(stubPlayer.getPosition()).thenReturn(position);

        controller.update(stubGame, GUI.ACTIONS.NONE, 501);

        Assertions.assertEquals(6, wheelbarrow.getPosition().getX());
        Assertions.assertEquals(5, wheelbarrow.getPosition().getY());

        Mockito.when(stubPlayer.getLastLR()).thenReturn(Element.DIRECTION.LEFT);

        controller.update(stubGame, GUI.ACTIONS.NONE, 501);

        Assertions.assertEquals(4, wheelbarrow.getPosition().getX());
        Assertions.assertEquals(5, wheelbarrow.getPosition().getY());

    }
}
