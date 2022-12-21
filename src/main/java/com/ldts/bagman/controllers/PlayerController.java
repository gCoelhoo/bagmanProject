package com.ldts.bagman.controllers;

import com.ldts.bagman.Game;
import com.ldts.bagman.gui.GUI;
import com.ldts.bagman.models.Elements.Element;
import com.ldts.bagman.models.Elements.Player;
import com.ldts.bagman.models.Elements.Object;
import com.ldts.bagman.models.MapModels.Ladder;
import com.ldts.bagman.models.MapModels.Map;
import com.ldts.bagman.models.MapModels.Wall;
import com.ldts.bagman.models.Position;
import com.ldts.bagman.sounds.GrabEffect;
import com.ldts.bagman.sounds.SoundEffect;
import com.ldts.bagman.sounds.StairEffect;
import com.ldts.bagman.sounds.StepEffect;

import java.util.ArrayList;
import java.util.List;

public class PlayerController extends ElementController {
    private SoundEffect stepSound = new StepEffect();
    private SoundEffect grabSound = new GrabEffect();
    private SoundEffect stairSound = new StairEffect();

    /*private void playerGetHurt(Map map){
        if(map.getPlayer().isKO())
            map.getPlayer().setDamage();
    }*/

    private boolean playerCanMove(Map map, Position pos) {
        for (Wall w : map.getWalls()) {
            if (w.getPosition().equals(pos)) {
                return false;
            }
        }
        return true;
    }
    private boolean playerIsOnLadder(Map map) {
        for (Ladder l : map.getLadders()) {
            if (l.getPosition().equals(map.getPlayer().getPosition())) {
                return true;
            }
        }
        return false;
    }
    private int speed = 1;
    private Object nearestObject(List<Object> objects, Player player) {
        float min = 3;
        int index = 0;
        int i = 0;
        for (Object o : objects) {
            if (o.getPosition().distance(player.getPosition()) < min) {
                index = i;
                min = o.getPosition().distance(player.getPosition());
            }
            i += 1;
        }
        return objects.get(index);
    }
    public void update(Game game, GUI.ACTIONS action, long time) {
        Map map = game.getState().getGameMap();
        Player player = map.getPlayer();
//        playerGetHurt(map);
        if (action == GUI.ACTIONS.USE) {
            if (player.isCarryingObject()) {
                Object o = player.dropObject();
                grabSound.play();
                o.setBeingCarried(false);
            }
            else {
                List<Object> nearby_objects = new ArrayList<>();
                boolean object_nearby_exists = false;
                List<Object> objects;
                objects = map.getObjects();
                for (Object ob : objects) {
                    Position p_pos = player.getPosition();
                    Position o_pos = ob.getPosition();
                    if (p_pos.distance(o_pos) < 2) {
                        nearby_objects.add(ob);
                        object_nearby_exists = true;
                    }
                }
                if (object_nearby_exists) {
                    Object obj = nearestObject(nearby_objects, player);
                    obj.setBeingCarried(true);
                    player.setCarryingObject(obj);
                    obj.setPosition(player.getPosition());
                    grabSound.play();
                }
            }
        }
        if (!(playerIsOnLadder(map)) && positionHasNoGround(map, player.getPosition())) {
            Position pos = new Position(player.getPosition().getX(), player.getPosition().getY() + speed);
            if (positionHasNoGround(map, pos)) {
                pos = new Position(player.getPosition().getX(), player.getPosition().getY() + speed*2);
            }
            player.setPosition(pos);
            if (player.isCarryingObject()) {
                player.getObject().setPosition(pos);
            }
            return;
        }
        if (action == GUI.ACTIONS.LEFT) {
            map.getPlayer().setPoints(10);
            Position pos = new Position(player.getPosition().getX() - speed, player.getPosition().getY());
            if (playerCanMove(map, pos)) {
                player.setPosition(pos);
                if (playerIsOnLadder(map))
                    stairSound.play();
                else
                    stepSound.play();
                if (player.isCarryingObject()) {
                    player.getObject().setPosition(pos);
                }
            }
            player.setLastLR(Element.DIRECTION.LEFT);
        }
        else if (action == GUI.ACTIONS.RIGHT) {
            map.getPlayer().setPoints(10);
            Position pos = new Position(player.getPosition().getX() + speed, player.getPosition().getY());
            if (playerCanMove(map, pos)) {
                player.setPosition(pos);
                if (playerIsOnLadder(map))
                    stairSound.play();
                else
                    stepSound.play();
                if (player.isCarryingObject()) {
                    player.getObject().setPosition(pos);
                }
            }
            player.setLastLR(Element.DIRECTION.RIGHT);
        }
        else if (action == GUI.ACTIONS.UP) {
            Position pos = new Position(player.getPosition().getX(), player.getPosition().getY() - speed);
            if (playerCanMove(map, pos) && playerIsOnLadder(map)) {
                player.setPosition(pos);
                stairSound.play();
                if (player.isCarryingObject()) {
                    player.getObject().setPosition(pos);
                }
            }
        }
        else if (action == GUI.ACTIONS.DOWN) {
            Position pos = new Position(player.getPosition().getX(), player.getPosition().getY() + speed);
            if (playerCanMove(map, pos) && playerIsOnLadder(map)) {
                player.setPosition(pos);
                stairSound.play();
                if (player.isCarryingObject()) {
                    player.getObject().setPosition(pos);
                }
            }
        }
    }

}