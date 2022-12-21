package com.ldts.bagman.Controllers;

import com.ldts.bagman.Game;
import com.ldts.bagman.controllers.GameMapController;
import com.ldts.bagman.controllers.PlayerController;
import com.ldts.bagman.gui.GUI;
import com.ldts.bagman.models.Elements.*;
import com.ldts.bagman.models.Elements.Object;
import com.ldts.bagman.models.MapModels.Ladder;
import com.ldts.bagman.models.MapModels.Map;
import com.ldts.bagman.models.MapModels.Wall;
import com.ldts.bagman.models.Position;
import com.ldts.bagman.states.GameState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class GameMapControllerTest {
    private Player player;
    private Map map;
    private Game game;
    private GameMapController gameMapController;


    @BeforeEach
    void setup() {
        player = new Player(35, 0);
        game = Mockito.mock(Game.class);
        map = new Map(72,20);
        gameMapController = new GameMapController();
        List<Wall> walls = new ArrayList<>();
        walls.add(new Wall(34, 1));
        walls.add(new Wall(35, 1));
        walls.add(new Wall(36, 1));
        walls.add(new Wall(37, 1));
        map.setWalls(walls);
        List<Ladder> ladders = new ArrayList<>();
        ladders.add(new Ladder(33, 1));
        map.setLadders(ladders);
        List<Object> objects = new ArrayList<>();
        objects.add(new CoinBag(34, 0));
        objects.add(new Pickaxe(37, 0));
        objects.add(new Wheelbarrow(36, 0));
        map.setObjects(objects);
        List<Policeman> policemen = new ArrayList<>();
        policemen.add(new Policeman(0, 0));
        map.setPolicemen(policemen);
        map.setPlayer(player);
        Mockito.when(game.getState()).thenReturn(new GameState(map));
    }

    @Test
    void mapScrollTest() {
        gameMapController.update(game, GUI.ACTIONS.NONE, 0); //no action
        Assertions.assertTrue(player.getPosition().equals(new Position(35, 0)));
        Assertions.assertTrue(game.getState().getGameMap().getWalls().get(0).getPosition().equals(new Position(34, 1)));
        Assertions.assertTrue(game.getState().getGameMap().getWalls().get(1).getPosition().equals(new Position(35, 1)));
        Assertions.assertTrue(game.getState().getGameMap().getWalls().get(2).getPosition().equals(new Position(36, 1)));
        Assertions.assertTrue(game.getState().getGameMap().getWalls().get(3).getPosition().equals(new Position(37, 1)));
        Assertions.assertTrue(game.getState().getGameMap().getLadders().get(0).getPosition().equals(new Position(33, 1)));
        Assertions.assertTrue(game.getState().getGameMap().getObjects().get(0).getPosition().equals(new Position(34, 0)));
        Assertions.assertTrue(game.getState().getGameMap().getObjects().get(1).getPosition().equals(new Position(37, 0)));
        Assertions.assertTrue(game.getState().getGameMap().getObjects().get(2).getPosition().equals(new Position(36, 0)));

        player.setPosition(new Position(36, 0)); //player moves to the right, screen should scroll
        gameMapController.update(game, GUI.ACTIONS.NONE, 0);
        Assertions.assertTrue(player.getPosition().equals(new Position(0, 0)));
        Assertions.assertTrue(game.getState().getGameMap().getWalls().get(0).getPosition().equals(new Position(-2, 1)));
        Assertions.assertTrue(game.getState().getGameMap().getWalls().get(1).getPosition().equals(new Position(-1, 1)));
        Assertions.assertTrue(game.getState().getGameMap().getWalls().get(2).getPosition().equals(new Position(0, 1)));
        Assertions.assertTrue(game.getState().getGameMap().getWalls().get(3).getPosition().equals(new Position(1, 1)));
        Assertions.assertTrue(game.getState().getGameMap().getLadders().get(0).getPosition().equals(new Position(-3, 1)));
        Assertions.assertTrue(game.getState().getGameMap().getObjects().get(0).getPosition().equals(new Position(-2, 0)));
        Assertions.assertTrue(game.getState().getGameMap().getObjects().get(1).getPosition().equals(new Position(1, 0)));
        Assertions.assertTrue(game.getState().getGameMap().getObjects().get(2).getPosition().equals(new Position(0, 0)));

        player.setPosition(new Position(-1, 0)); //player moves to the left again, screen should scroll
        gameMapController.update(game, GUI.ACTIONS.NONE, 0);
        Assertions.assertTrue(player.getPosition().equals(new Position(35, 0)));
        Assertions.assertTrue(game.getState().getGameMap().getWalls().get(0).getPosition().equals(new Position(34, 1)));
        Assertions.assertTrue(game.getState().getGameMap().getWalls().get(1).getPosition().equals(new Position(35, 1)));
        Assertions.assertTrue(game.getState().getGameMap().getWalls().get(2).getPosition().equals(new Position(36, 1)));
        Assertions.assertTrue(game.getState().getGameMap().getWalls().get(3).getPosition().equals(new Position(37, 1)));
        Assertions.assertTrue(game.getState().getGameMap().getLadders().get(0).getPosition().equals(new Position(33, 1)));
        Assertions.assertTrue(game.getState().getGameMap().getObjects().get(0).getPosition().equals(new Position(34, 0)));
        Assertions.assertTrue(game.getState().getGameMap().getObjects().get(1).getPosition().equals(new Position(37, 0)));
        Assertions.assertTrue(game.getState().getGameMap().getObjects().get(2).getPosition().equals(new Position(36, 0)));
    }
}
