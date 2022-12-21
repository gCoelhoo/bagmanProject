package com.ldts.bagman.views;

import com.googlecode.lanterna.SGR;
import com.ldts.bagman.gui.GUI;
import com.ldts.bagman.models.Elements.Pickaxe;
import com.ldts.bagman.models.Elements.Player;
import com.ldts.bagman.models.MapModels.Map;
import com.ldts.bagman.models.Menu.Menu;
import com.ldts.bagman.models.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ViewerTest {
    Map map;
    Menu menu;
    Viewer viewer;
    GUI gui;

    @BeforeEach
    public void setup(){
        map = Mockito.mock(Map.class);
        menu = Mockito.mock(Menu.class);
        viewer = new Viewer(map, menu);
        gui = Mockito.mock(GUI.class);
    }

    @Test
    public void viewerTest() throws IOException {
        Mockito.when(map.getPlayer()).thenReturn(new Player(0, 0));
        Mockito.when(menu.getPreviousMenu()).thenReturn(new Menu(menu));

        viewer.draw(gui);
        viewer.drawHelp(gui);
        Mockito.verify(gui, Mockito.times(23)).drawText(Mockito.any(Position.class), Mockito.anyString(),
                Mockito.anyString(), Mockito.anyString(), Mockito.any(SGR.class));
    }
    @Test
    public void viewerTest2() throws IOException {
        Mockito.when(map.getPlayer()).thenReturn(new Player(0, 0));
        Mockito.when(map.getObjects()).thenReturn(List.of(new Pickaxe(1, 1)));

        viewer.draw(gui);
        viewer.drawHelp(gui);
        Mockito.verify(gui, Mockito.times(17)).drawText(Mockito.any(Position.class), Mockito.anyString(),
                Mockito.anyString(), Mockito.anyString(), Mockito.any(SGR.class));
    }
}
