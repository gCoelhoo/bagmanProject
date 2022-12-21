package com.ldts.bagman.views;

import com.ldts.bagman.gui.GUI;
import com.ldts.bagman.models.Elements.Object;

public class ObjectView implements ElementViewer<Object> {
    @Override
    public void draw(Object object, GUI lanternaGUI) {
        if (object.type() == Object.TYPE.COINBAG) {
            lanternaGUI.coinBagDraw(object.getPosition());
        }
        else if (object.type() == Object.TYPE.PICKAXE) {
            lanternaGUI.pickaxeDraw(object.getPosition());
        }
        else if (object.type() == Object.TYPE.WHEELBARROW) {
            lanternaGUI.wheelbarrowDraw(object.getPosition());
        }
    }
}
