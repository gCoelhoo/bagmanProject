package com.ldts.bagman.controllers;

import com.ldts.bagman.Game;
import com.ldts.bagman.controllers.ElementController;
import com.ldts.bagman.gui.GUI;
import com.ldts.bagman.models.Elements.Element;
import com.ldts.bagman.models.Elements.Object;
import com.ldts.bagman.models.MapModels.Map;
import com.ldts.bagman.models.Position;
import com.ldts.bagman.sounds.CoinEffect;
import com.ldts.bagman.sounds.SoundEffect;

import java.util.ArrayList;
import java.util.List;

public class PickaxeController extends ElementController {
    private final int speed = 1;
    private SoundEffect coinSound = new CoinEffect();
    public void update(Game game, GUI.ACTIONS action, long time) {
        Map map = game.getState().getGameMap();
        for (Object o : map.getObjects()) {
            if (o.type() == Object.TYPE.PICKAXE) {
                if (positionHasNoGround(map, o.getPosition()) && !(o.isBeingCarried())) {
                    Position pos = new Position(o.getPosition().getX(), o.getPosition().getY() + speed);
                    if (positionHasNoGround(map, pos)) {
                        pos = new Position(o.getPosition().getX(), o.getPosition().getY() + speed*2);
                    }
                    o.setPosition(pos);
                }
                else if (o.isBeingCarried()) {
                    if (map.getPlayer().getLastLR() == Element.DIRECTION.RIGHT) {
                        o.setPosition(new Position(map.getPlayer().getPosition().getX() + 1, map.getPlayer().getPosition().getY()));
                    }
                    else {
                        o.setPosition(new Position(map.getPlayer().getPosition().getX() - 1, map.getPlayer().getPosition().getY()));
                    }
                }
            }
        }
    }
}
