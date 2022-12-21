package com.ldts.bagman.views;

import com.ldts.bagman.gui.GUI;
import com.ldts.bagman.models.Elements.Policeman;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PolicemenViewTest {
    @Test
    public void guardDraw(){
        Policeman policeman = new Policeman(1,1);
        GUI lanternaGUI = Mockito.mock(GUI.class);
        PolicemenView gView = new PolicemenView();

        gView.draw(policeman, lanternaGUI);
        Mockito.verify(lanternaGUI).policemanDraw(policeman.getPosition());
    }
}
