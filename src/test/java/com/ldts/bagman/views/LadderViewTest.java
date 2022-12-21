package com.ldts.bagman.views;

import com.ldts.bagman.gui.GUI;
import com.ldts.bagman.models.MapModels.Ladder;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class LadderViewTest {
    @Test
    public void ladderDraw(){
        Ladder ladder = new Ladder(1,1);
        GUI lanternaGUI = Mockito.mock(GUI.class);
        LadderView lView = new LadderView();

        lView.draw(ladder, lanternaGUI);
        Mockito.verify(lanternaGUI).ladderDraw(ladder.getPosition());
    }
}
