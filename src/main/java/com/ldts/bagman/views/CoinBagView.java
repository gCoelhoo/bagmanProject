package com.ldts.bagman.views;

import com.ldts.bagman.gui.GUI;
import com.ldts.bagman.models.Elements.CoinBag;

public class CoinBagView implements ElementViewer<CoinBag> {
    @Override
    public void draw(CoinBag object, GUI lanternaGUI) {
        lanternaGUI.coinBagDraw(object.getPosition());
    }
}
