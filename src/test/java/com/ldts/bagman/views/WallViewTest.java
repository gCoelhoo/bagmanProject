package com.ldts.bagman.views;

import com.ldts.bagman.gui.GUI;
import com.ldts.bagman.models.MapModels.Wall;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class WallViewTest {
    @Test
    public void wallDraw(){
        Wall wall = new Wall(1,1);
        GUI lanternaGUI = Mockito.mock(GUI.class);
        WallView wView = new WallView();

        wView.draw(wall,lanternaGUI);
        Mockito.verify(lanternaGUI).wallDraw(wall.getPosition());
    }
}
