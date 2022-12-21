package com.ldts.bagman.controllers;

import com.ldts.bagman.models.MapModels.Map;
import com.ldts.bagman.models.MapModels.Wall;
import com.ldts.bagman.models.Position;

public abstract class ElementController extends Controller {

    protected boolean positionHasNoGround(Map map, Position pos) {
        for (Wall w : map.getWalls()) {
            if ((w.getPosition().getX() == pos.getX()) && (w.getPosition().getY() == pos.getY() + 1)) {
                return false;
            }
        }
        return true;
    }
}
