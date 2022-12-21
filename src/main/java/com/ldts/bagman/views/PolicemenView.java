package com.ldts.bagman.views;

import com.ldts.bagman.gui.GUI;
import com.ldts.bagman.models.Elements.Policeman;

public class PolicemenView implements ElementViewer<Policeman>{
    @Override
    public void draw(Policeman object, GUI lanternaGUI) {
        lanternaGUI.policemanDraw(object.getPosition());
    }
}
