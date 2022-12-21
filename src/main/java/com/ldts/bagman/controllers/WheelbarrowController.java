package com.ldts.bagman.controllers;

import com.ldts.bagman.Game;
import com.ldts.bagman.gui.GUI;
import com.ldts.bagman.models.Elements.Element;
import com.ldts.bagman.models.Elements.Object;
import com.ldts.bagman.models.MapModels.Map;
import com.ldts.bagman.models.Position;

public class WheelbarrowController extends ElementController {

    private final int speed = 1;

    public void update(Game game, GUI.ACTIONS action, long time) {
        Map map = game.getState().getGameMap();
        Object w = null;
        for (Object o : map.getObjects()) {
            if (o.type() == Object.TYPE.WHEELBARROW) {
                w = o;
            }
        }
        if (positionHasNoGround(map, w.getPosition())) {
            Position pos = new Position(w.getPosition().getX(), w.getPosition().getY() + speed);
            if (positionHasNoGround(map, pos)) {
                pos = new Position(w.getPosition().getX(), w.getPosition().getY() + speed * 2);
            }
            w.setPosition(pos);
            if (w.isBeingCarried()) {
                w.setBeingCarried(false);
                map.getPlayer().dropObject();
            }
        }
        else if (w.isBeingCarried()) {
            if (map.getPlayer().getLastLR() == Element.DIRECTION.RIGHT) {
                w.setPosition(new Position(map.getPlayer().getPosition().getX() + 1, map.getPlayer().getPosition().getY()));
            }
            else {
                w.setPosition(new Position(map.getPlayer().getPosition().getX() - 1, map.getPlayer().getPosition().getY()));
            }
        }
    }
}
