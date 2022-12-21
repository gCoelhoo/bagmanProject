package com.ldts.bagman.models.Menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class HelpTest {
    private Menu stubMenu = Mockito.mock(Menu.class);
    private Help help;

    @BeforeEach
    public void setup() {
        help = new Help(stubMenu);
    }

    @Test
    public void getPreviousMenu() {
        Assertions.assertEquals(stubMenu, help.getPreviousMenu());
    }

    @Test
    public void getPointer() {
        Assertions.assertEquals(3, help.getPointer());
    }
}
