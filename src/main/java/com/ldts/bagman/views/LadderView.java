package com.ldts.bagman.views;

import com.ldts.bagman.gui.GUI;
import com.ldts.bagman.models.MapModels.Ladder;

public class LadderView implements ElementViewer<Ladder> {
    @Override
    public void draw(Ladder object, GUI lanternaGUI) {
        lanternaGUI.ladderDraw(object.getPosition());
    }
}
