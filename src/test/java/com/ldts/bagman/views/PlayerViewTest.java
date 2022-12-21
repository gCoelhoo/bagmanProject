package com.ldts.bagman.views;

import com.ldts.bagman.gui.GUI;
import com.ldts.bagman.models.Elements.Player;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PlayerViewTest {
    @Test
    public void playerDraw(){
        Player player = new Player(1, 1);
        GUI lanternaGUI = Mockito.mock(GUI.class);
        PlayerView pView = new PlayerView();

        pView.draw(player, lanternaGUI);
        Mockito.verify(lanternaGUI).playerDraw(player.getPosition());
    }
}
