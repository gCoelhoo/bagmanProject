package com.ldts.bagman.views.Menu;

import com.googlecode.lanterna.SGR;
import com.ldts.bagman.gui.GUI;
import com.ldts.bagman.models.Menu.Menu;
import com.ldts.bagman.models.Position;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MenuViewTest {
    @Test
    public void menuDraw(){
        Menu menu = Mockito.mock(Menu.class);
        GUI lanternaGUI = Mockito.mock(GUI.class);

        //test coverage for different menu pointer values
        for(int i = 0; i < 3; ++i) {
            Mockito.when(menu.getPointer()).thenReturn(i);

            MenuView menuView = new MenuView(menu, lanternaGUI);

            menuView.drawer(lanternaGUI);
            Mockito.verify(lanternaGUI, Mockito.times(6*(i+1))).drawText(Mockito.any(Position.class), Mockito.anyString(),
                    Mockito.anyString(), Mockito.anyString(), Mockito.any(SGR.class));
        }
    }
    @Test
    public void helpDraw(){
        GUI lanternaGUI = Mockito.mock(GUI.class);
        HelpView helpView = new HelpView(lanternaGUI);

        helpView.drawButton(lanternaGUI);
        Mockito.verify(lanternaGUI, Mockito.times(10)).drawText(Mockito.any(Position.class), Mockito.anyString(),
                Mockito.anyString(), Mockito.anyString(), Mockito.any(SGR.class));
    }
}
