package com.ldts.bagman;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.ldts.bagman.gui.GUI.ACTIONS;
import com.ldts.bagman.gui.LanternaGUI;
import com.ldts.bagman.models.Elements.Object;
import com.ldts.bagman.models.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class LanternaGUITest {
    private Screen stubScreen = Mockito.mock(Screen.class);
    private TextGraphics stubGraphics = Mockito.mock(TextGraphics.class);
    private Position stubPosition = Mockito.mock(Position.class);

    private LanternaGUI lanternaGUI;

    @BeforeEach
    public void setup() {
        lanternaGUI = new LanternaGUI(stubScreen, 20, 40);
    }

    @Test
    public void getNextAction() throws IOException {
        KeyStroke stubKey = Mockito.mock(KeyStroke.class);

        Mockito.when(stubScreen.pollInput()).thenReturn(null);
        Assertions.assertEquals(ACTIONS.NONE, lanternaGUI.getNextAction());

        Mockito.when(stubScreen.pollInput()).thenReturn(stubKey);
        Mockito.when(stubKey.getKeyType()).thenReturn(KeyType.EOF);
        Assertions.assertEquals(ACTIONS.QUIT, lanternaGUI.getNextAction());

        Mockito.when(stubScreen.pollInput()).thenReturn(stubKey);
        Mockito.when(stubKey.getKeyType()).thenReturn(KeyType.Escape);
        Assertions.assertEquals(ACTIONS.QUIT, lanternaGUI.getNextAction());

        Mockito.when(stubScreen.pollInput()).thenReturn(stubKey);
        Mockito.when(stubKey.getKeyType()).thenReturn(KeyType.ArrowUp);
        Assertions.assertEquals(ACTIONS.UP, lanternaGUI.getNextAction());

        Mockito.when(stubScreen.pollInput()).thenReturn(stubKey);
        Mockito.when(stubKey.getKeyType()).thenReturn(KeyType.ArrowDown);
        Assertions.assertEquals(ACTIONS.DOWN, lanternaGUI.getNextAction());

        Mockito.when(stubScreen.pollInput()).thenReturn(stubKey);
        Mockito.when(stubKey.getKeyType()).thenReturn(KeyType.ArrowRight);
        Assertions.assertEquals(ACTIONS.RIGHT, lanternaGUI.getNextAction());

        Mockito.when(stubScreen.pollInput()).thenReturn(stubKey);
        Mockito.when(stubKey.getKeyType()).thenReturn(KeyType.ArrowLeft);
        Assertions.assertEquals(ACTIONS.LEFT, lanternaGUI.getNextAction());

        Mockito.when(stubScreen.pollInput()).thenReturn(stubKey);
        Mockito.when(stubKey.getKeyType()).thenReturn(KeyType.Enter);
        Assertions.assertEquals(ACTIONS.USE, lanternaGUI.getNextAction());

        Mockito.when(stubScreen.pollInput()).thenReturn(stubKey);
        Mockito.when(stubKey.getCharacter()).thenReturn('x');
        Assertions.assertEquals(ACTIONS.USE, lanternaGUI.getNextAction());
    }

    @Test
    public void wallDraw() {
        Mockito.when(stubScreen.newTextGraphics()).thenReturn(stubGraphics);
        lanternaGUI.wallDraw(stubPosition);
        Mockito.verify(stubGraphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#B88600"));
        Mockito.verify(stubGraphics, Mockito.times(1)).setCharacter(stubPosition.getX(), stubPosition.getY(), Symbols.BLOCK_DENSE);
    }

    @Test
    public void playerDraw() {
        Mockito.when(stubScreen.newTextGraphics()).thenReturn(stubGraphics);
        lanternaGUI.playerDraw(stubPosition);
        Mockito.verify(stubGraphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#E00000"));
        Mockito.verify(stubGraphics, Mockito.times(1)).putString(stubPosition.getX(), stubPosition.getY(), Character.toString(0xE13D));
    }

    @Test
    public void policemanDraw() {
        Mockito.when(stubScreen.newTextGraphics()).thenReturn(stubGraphics);
        lanternaGUI.policemanDraw(stubPosition);
        Mockito.verify(stubGraphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#0000E0"));
        Mockito.verify(stubGraphics, Mockito.times(1)).putString(stubPosition.getX(), stubPosition.getY(), Character.toString(0x26D1));
    }

    @Test
    public void coinBagDraw() {
        Mockito.when(stubScreen.newTextGraphics()).thenReturn(stubGraphics);
        lanternaGUI.coinBagDraw(stubPosition);
        Mockito.verify(stubGraphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#C0C030"));
        Mockito.verify(stubGraphics, Mockito.times(1)).putString(stubPosition.getX(), stubPosition.getY(), "$", SGR.CIRCLED, SGR.BOLD);
    }

    @Test
    public void wheelbarrowDraw() {
        Mockito.when(stubScreen.newTextGraphics()).thenReturn(stubGraphics);
        lanternaGUI.wheelbarrowDraw(stubPosition);
        Mockito.verify(stubGraphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#822020"));
        Mockito.verify(stubGraphics, Mockito.times(1)).putString(stubPosition.getX(), stubPosition.getY(), Character.toString(0x26DF));
    }

    @Test
    public void pickaxeDraw() {
        Mockito.when(stubScreen.newTextGraphics()).thenReturn(stubGraphics);
        lanternaGUI.pickaxeDraw(stubPosition);
        Mockito.verify(stubGraphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#C0C0C0"));
        Mockito.verify(stubGraphics, Mockito.times(1)).putString(stubPosition.getX(), stubPosition.getY(), Character.toString(0x26CF));
    }

    @Test
    public void ladderDraw() {
        Mockito.when(stubScreen.newTextGraphics()).thenReturn(stubGraphics);
        lanternaGUI.ladderDraw(stubPosition);
        Mockito.verify(stubGraphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#603030"));
        Mockito.verify(stubGraphics, Mockito.times(1)).putString(stubPosition.getX(), stubPosition.getY(), Character.toString(0x210D));
    }

    @Test
    public void drawText() {
        Mockito.when(stubScreen.newTextGraphics()).thenReturn(stubGraphics);
        String content = "Test";
        String colorText = "#B22222";
        String colorBg = "#FFD700";
        SGR style = SGR.BOLD;
        lanternaGUI.drawText(stubPosition, content, colorText, colorBg, SGR.BOLD);
        Mockito.verify(stubGraphics, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString(colorBg));
        Mockito.verify(stubGraphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString(colorText));
        Mockito.verify(stubGraphics, Mockito.times(1)).putString(stubPosition.getX(), stubPosition.getY(), content, style);
    }

    @Test
    public void drawElementInterface() {
        Mockito.when(stubScreen.newTextGraphics()).thenReturn(stubGraphics);
        int codepoint = 0x2764;
        String color = "#FF0000";
        lanternaGUI.drawElementInterface(stubPosition, codepoint, color);
        Mockito.verify(stubGraphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString(color));
        Mockito.verify(stubGraphics, Mockito.times(1)).putString(stubPosition.getX(), stubPosition.getY(), Character.toString(codepoint));
    }

    @Test
    public void clear() {
        lanternaGUI.clear();
        Mockito.verify(stubScreen, Mockito.times(1)).clear();
    }

    @Test
    public void refresh() throws IOException {
        lanternaGUI.refresh();
        Mockito.verify(stubScreen, Mockito.times(1)).refresh();
    }

    @Test
    public void close() throws IOException {
        lanternaGUI.close();
        Mockito.verify(stubScreen, Mockito.times(1)).close();
    }

    @Test
    public void getWidth() {
        Assertions.assertEquals(20, lanternaGUI.getWidth());
    }

    @Test
    public void getHeight() {
        Assertions.assertEquals(40, lanternaGUI.getHeight());
    }

    @Test
    public void lanternaGUI() throws IOException {
        LanternaGUI constructor = new LanternaGUI(20, 40);
        Assertions.assertEquals(20, constructor.getWidth());
        Assertions.assertEquals(40, constructor.getHeight());
    }
}
