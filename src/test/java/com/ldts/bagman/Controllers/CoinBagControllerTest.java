package com.ldts.bagman.Controllers;

import com.ldts.bagman.Game;
import com.ldts.bagman.controllers.CoinBagController;
import com.ldts.bagman.controllers.ElementController;
import com.ldts.bagman.gui.GUI;
import com.ldts.bagman.models.Elements.*;
import com.ldts.bagman.models.Elements.Object;
import com.ldts.bagman.models.MapModels.Map;
import com.ldts.bagman.models.MapModels.Wall;
import com.ldts.bagman.models.Position;
import com.ldts.bagman.sounds.SoundEffect;
import com.ldts.bagman.states.State;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CoinBagControllerTest {
    private Game stubGame = Mockito.mock(Game.class);
    private State stubState = Mockito.mock(State.class);
    private Map stubMap = Mockito.mock(Map.class);
    private Player stubPlayer = Mockito.mock(Player.class);
    private CoinBagController controller;
    private CoinBag coin;


    @BeforeEach
    public void setupCoin() throws IOException {
        coin = new CoinBag(1,2);
        controller = new CoinBagController();
    }

    @Test
    public void coinInWheelBarrow(){
        Wheelbarrow wheelbarrow = new Wheelbarrow(1,2);
        coin.setBeingCarried(false);
        List<Object> list = new LinkedList<Object>(Arrays.asList(coin, wheelbarrow));

        Mockito.when(stubGame.getState()).thenReturn(stubState);
        Mockito.when(stubState.getGameMap()).thenReturn(stubMap);
        Mockito.when(stubMap.getObjects()).thenReturn(list);
        Mockito.when(stubMap.getPlayer()).thenReturn(stubPlayer);

        controller.update(stubGame, GUI.ACTIONS.NONE, 501);

        Mockito.verify(stubPlayer, Mockito.times(1)).setPoints(3000);
    }

    @Test
    public void noGround(){
        Wheelbarrow wheelbarrow = new Wheelbarrow(7,2);
        Wall wall = new Wall(1, 4);
        coin.setBeingCarried(false);
        List<Object> listCoin = new LinkedList<Object>(Arrays.asList(coin, wheelbarrow));
        List<Wall> listWall = new LinkedList<Wall>(Arrays.asList(wall));

        Mockito.when(stubGame.getState()).thenReturn(stubState);
        Mockito.when(stubState.getGameMap()).thenReturn(stubMap);
        Mockito.when(stubMap.getObjects()).thenReturn(listCoin);
        Mockito.when(stubMap.getWalls()).thenReturn(listWall);

        controller.update(stubGame, GUI.ACTIONS.NONE, 501);

        Assertions.assertEquals(1, coin.getPosition().getX());
        Assertions.assertEquals(3, coin.getPosition().getY());

        wall.setPosition(new Position(1,7));
        coin.setPosition(new Position(1,2));

        controller.update(stubGame, GUI.ACTIONS.NONE, 501);

        Assertions.assertEquals(1, coin.getPosition().getX());
        Assertions.assertEquals(4, coin.getPosition().getY());
    }

    @Test
    public void coinBeingCarried(){
        coin.setBeingCarried(true);
        List<Object> list = new LinkedList<Object>(Arrays.asList(coin));
        Position position = new Position(5,5);

        Mockito.when(stubGame.getState()).thenReturn(stubState);
        Mockito.when(stubState.getGameMap()).thenReturn(stubMap);
        Mockito.when(stubMap.getObjects()).thenReturn(list);
        Mockito.when(stubMap.getPlayer()).thenReturn(stubPlayer);
        Mockito.when(stubPlayer.getLastLR()).thenReturn(Element.DIRECTION.RIGHT);
        Mockito.when(stubPlayer.getPosition()).thenReturn(position);

        controller.update(stubGame, GUI.ACTIONS.NONE, 501);

        Assertions.assertEquals(4, coin.getPosition().getX());
        Assertions.assertEquals(5, coin.getPosition().getY());

        Mockito.when(stubPlayer.getLastLR()).thenReturn(Element.DIRECTION.LEFT);

        controller.update(stubGame, GUI.ACTIONS.NONE, 501);

        Assertions.assertEquals(6, coin.getPosition().getX());
        Assertions.assertEquals(5, coin.getPosition().getY());

    }
}
