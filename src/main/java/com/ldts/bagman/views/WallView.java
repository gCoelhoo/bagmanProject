package com.ldts.bagman.views;

import com.ldts.bagman.gui.GUI;
import com.ldts.bagman.models.MapModels.Wall;

public class WallView implements ElementViewer<Wall>{
    @Override
    public void draw(Wall object, GUI lanternaGUI) {
        lanternaGUI.wallDraw(object.getPosition());

    }
}
