package com.ldts.bagman.Controllers;

import com.ldts.bagman.Game;
import com.ldts.bagman.controllers.PlayerController;
import com.ldts.bagman.gui.GUI;
import com.ldts.bagman.models.Elements.CoinBag;
import com.ldts.bagman.models.Elements.Object;
import com.ldts.bagman.models.Elements.Player;
import com.ldts.bagman.models.MapModels.Ladder;
import com.ldts.bagman.models.MapModels.Map;
import com.ldts.bagman.models.MapModels.MapCreator;
import com.ldts.bagman.models.MapModels.Wall;
import com.ldts.bagman.models.Position;
import com.ldts.bagman.states.GameState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class PlayerControllerTest {
    private Player player;
    private Map map;
    private Game game;
    private PlayerController playerController;


    @BeforeEach
    void setup() {
        player = new Player(0, 0);
        game = Mockito.mock(Game.class);
        map = new Map(20,20);
        playerController = new PlayerController();
        List<Wall> walls = new ArrayList<>();
        walls.add(new Wall(1, 1));
        walls.add(new Wall(2, 1));
        walls.add(new Wall(5, 2));
        walls.add(new Wall(6, 2));
        map.setWalls(walls);
        List<Ladder> ladders = new ArrayList<>();
        ladders.add(new Ladder(3, 1));
        ladders.add(new Ladder(3, 2));
        map.setLadders(ladders);
        List<Object> objects = new ArrayList<>();
        objects.add(new CoinBag(5, 1));
        objects.add(new CoinBag(6, 1));
        map.setObjects(objects);
        map.setPlayer(player);
        Mockito.when(game.getState()).thenReturn(new GameState(map));
    }
    @Test
    public void testFall(){
        playerController.update(game, GUI.ACTIONS.NONE, 0);
        Assertions.assertFalse(player.getPosition().equals(new Position(0, 0)));
        Assertions.assertTrue(player.getPosition().equals(new Position(0, 2)));
    }
    @Test
    public void testWalk() {
        player.setPosition(new Position(1, 0));

        playerController.update(game, GUI.ACTIONS.NONE, 0); //no action
        Assertions.assertTrue(player.getPosition().equals(new Position(1, 0)));

        playerController.update(game, GUI.ACTIONS.RIGHT, 0); //moves right
        Assertions.assertTrue(player.getPosition().equals(new Position(2, 0)));
        playerController.update(game, GUI.ACTIONS.LEFT, 0); //moves left
        Assertions.assertTrue(player.getPosition().equals(new Position(1, 0)));
    }

    @Test
    public void testLadder() {
        player.setPosition(new Position(3, 1)); //set player on ladder

        playerController.update(game, GUI.ACTIONS.NONE, 0); //no action
        Assertions.assertTrue(player.getPosition().equals(new Position(3, 1)));

        playerController.update(game, GUI.ACTIONS.DOWN, 0); //moves down
        Assertions.assertTrue(player.getPosition().equals(new Position(3, 2)));
        playerController.update(game, GUI.ACTIONS.UP, 0); //moves up
        Assertions.assertTrue(player.getPosition().equals(new Position(3, 1)));

        player.setPosition(new Position(1, 0)); //set player on ground

        playerController.update(game, GUI.ACTIONS.DOWN, 0); //down arrow is clicked, but player shouldn't move
        Assertions.assertTrue(player.getPosition().equals(new Position(1, 0)));
        playerController.update(game, GUI.ACTIONS.UP, 0); //up arrow is clicked, but player shouldn't move
        Assertions.assertTrue(player.getPosition().equals(new Position(1, 0)));
    }

    @Test
    public void testObjectAction() {
        player.setPosition(new Position(5, 1));

        playerController.update(game, GUI.ACTIONS.NONE, 0); //no action, no object has moved or been picked up
        map = game.getState().getGameMap();
        Assertions.assertTrue(map.getObjects().get(0).getPosition().equals(new Position(5, 1)));
        Assertions.assertTrue(map.getObjects().get(1).getPosition().equals(new Position(6, 1)));
        Assertions.assertFalse(map.getObjects().get(0).isBeingCarried());
        Assertions.assertFalse(map.getObjects().get(1).isBeingCarried());
        Assertions.assertFalse(player.isCarryingObject());

        playerController.update(game, GUI.ACTIONS.USE, 500); //Player picks up item closest to him (should be the 1st)
        map = game.getState().getGameMap();
        Assertions.assertTrue(map.getObjects().get(0).isBeingCarried());
        Assertions.assertFalse(map.getObjects().get(1).isBeingCarried());
        Assertions.assertTrue(player.isCarryingObject());

        playerController.update(game, GUI.ACTIONS.USE, 500); //Player drops item
        map = game.getState().getGameMap();
        Assertions.assertFalse(map.getObjects().get(0).isBeingCarried());
        Assertions.assertFalse(map.getObjects().get(1).isBeingCarried());
        Assertions.assertFalse(player.isCarryingObject());

        player.setPosition(new Position(1, 0));
        playerController.update(game, GUI.ACTIONS.USE, 500); //Player tries to use item but is too far away
        map = game.getState().getGameMap();
        Assertions.assertFalse(map.getObjects().get(0).isBeingCarried());
        Assertions.assertFalse(map.getObjects().get(1).isBeingCarried());
        Assertions.assertFalse(player.isCarryingObject());
    }

}