package com.ldts.bagman.views;

import com.ldts.bagman.gui.GUI;
import com.ldts.bagman.models.Elements.Player;

public class PlayerView implements ElementViewer<Player> {
    @Override
    public void draw(Player object, GUI lanternaGUI) {
        lanternaGUI.playerDraw(object.getPosition());
    }

}
