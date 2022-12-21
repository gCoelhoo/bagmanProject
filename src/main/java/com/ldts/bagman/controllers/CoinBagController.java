package com.ldts.bagman.controllers;

import com.googlecode.lanterna.SGR;
import com.ldts.bagman.Game;
import com.ldts.bagman.gui.GUI;
import com.ldts.bagman.models.Elements.Element;
import com.ldts.bagman.models.Elements.Wheelbarrow;
import com.ldts.bagman.models.MapModels.Map;
import com.ldts.bagman.models.Elements.Object;
import com.ldts.bagman.models.MapModels.Wall;
import com.ldts.bagman.models.Position;
import com.ldts.bagman.sounds.CoinEffect;
import com.ldts.bagman.sounds.SoundEffect;

import javax.sound.midi.Soundbank;

import java.util.ArrayList;
import java.util.List;

public class CoinBagController extends ElementController {
    private final int speed = 1;
    private SoundEffect coinSound = new CoinEffect();
    public void update(Game game, GUI.ACTIONS action, long time) {
        Map map = game.getState().getGameMap();
        Object w = null;
        for (Object o : map.getObjects()) {
            if (o.type() == Object.TYPE.WHEELBARROW) {
                w = o;
            }
        }
        List<Object> temp = new ArrayList<>();
        for (Object o : map.getObjects()) {
            if (o.type() == Object.TYPE.COINBAG) {
                if (!(o.isBeingCarried()) && o.getPosition().equals(w.getPosition())) {
                    temp.add(o);
                    coinSound.play();
                }
                else if (positionHasNoGround(map, o.getPosition()) && !(o.isBeingCarried())) {
                    Position pos = new Position(o.getPosition().getX(), o.getPosition().getY() + speed);
                    if (positionHasNoGround(map, pos)) {
                        pos = new Position(o.getPosition().getX(), o.getPosition().getY() + speed*2);
                    }
                    o.setPosition(pos);
                }
                else if (o.isBeingCarried()) {
                    if (map.getPlayer().getLastLR() == Element.DIRECTION.RIGHT) {
                        o.setPosition(new Position(map.getPlayer().getPosition().getX() - 1, map.getPlayer().getPosition().getY()));
                    }
                    else {
                        o.setPosition(new Position(map.getPlayer().getPosition().getX() + 1, map.getPlayer().getPosition().getY()));
                    }
                }
            }
        }
        for (Object o : temp) {
            map.getObjects().remove(o);
            map.getPlayer().setPoints(3000);
        }
    }
}
