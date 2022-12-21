package com.ldts.bagman.views;

import com.ldts.bagman.gui.GUI;
import com.ldts.bagman.models.Elements.CoinBag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CoinBagViewTest {
    @Test
    public void coinBagDraw(){
        CoinBag coinBag = new CoinBag(1,1);
        GUI lanternaGUI = Mockito.mock(GUI.class);
        CoinBagView bagView = new CoinBagView();

        bagView.draw(coinBag, lanternaGUI);
        Mockito.verify(lanternaGUI).coinBagDraw(coinBag.getPosition());
    }
}
