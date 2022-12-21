package com.ldts.bagman.gui;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;
import com.ldts.bagman.models.Position;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class LanternaGUI implements GUI{
    private Screen screen;
    private int width;
    private int height;

    public LanternaGUI(Screen screen, int width, int height) {
        this.screen = screen;
        this.width = width;
        this.height = height;
    }

    public LanternaGUI(int width, int height) throws IOException {
        this.width = width;
        this.height = height;
        Terminal terminal = createTermimal(width, height);
        this.screen = createScreen(terminal);
    }

    private Terminal createTermimal(int width, int height) throws IOException {
        TerminalSize terminalSize = new TerminalSize(width, height);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize);
        terminalFactory.setTerminalEmulatorTitle("Bagman");
        terminalFactory.setPreferTerminalEmulator(true);

        SwingTerminalFontConfiguration config = SwingTerminalFontConfiguration.getDefaultOfSize(30);
        terminalFactory.setTerminalEmulatorFontConfiguration(config);
        terminalFactory.setForceAWTOverSwing(true);

        return terminalFactory.createTerminal();
    }

    private Screen createScreen(Terminal terminal) throws IOException {
        this.screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();

        return screen;
    }

    @Override
    public ACTIONS getNextAction() throws IOException{
        KeyStroke key = this.screen.pollInput();

        if(key == null) return ACTIONS.NONE;
        if(key.getKeyType() == KeyType.EOF ||
                key.getKeyType() == KeyType.Escape) return ACTIONS.QUIT;
        if(key.getKeyType() == KeyType.ArrowUp) return ACTIONS.UP;
        if(key.getKeyType() == KeyType.ArrowDown) return ACTIONS.DOWN;
        if(key.getKeyType() == KeyType.ArrowRight) return ACTIONS.RIGHT;
        if(key.getKeyType() == KeyType.ArrowLeft) return ACTIONS.LEFT;
        if(key.getKeyType() == KeyType.Enter || key.getCharacter() == 'x') return ACTIONS.USE;

        return ACTIONS.NONE;
    }

    @Override
    public void wallDraw(Position position){
        TextGraphics textGraphics = this.screen.newTextGraphics();
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#B88600"));
        textGraphics.setCharacter(position.getX(), position.getY(), Symbols.BLOCK_DENSE);
    }

    @Override
    public void playerDraw(Position position) {
        TextGraphics textGraphics = this.screen.newTextGraphics();
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#E00000"));
        textGraphics.putString(position.getX(), position.getY(), Character.toString(0xE13D));
    }
    @Override
    public void policemanDraw(Position position) {
        TextGraphics textGraphics = this.screen.newTextGraphics();
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#0000E0"));
        textGraphics.putString(position.getX(), position.getY(), Character.toString(0x26D1));
    }
    @Override
    public void coinBagDraw(Position position) {
        TextGraphics textGraphics = this.screen.newTextGraphics();
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#C0C030"));
        textGraphics.putString(position.getX(), position.getY(), "$", SGR.CIRCLED, SGR.BOLD);
    }
    @Override
    public void wheelbarrowDraw(Position position) {
        TextGraphics textGraphics = this.screen.newTextGraphics();
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#822020"));
        textGraphics.putString(position.getX(), position.getY(), Character.toString(0x26DF));
    }
    @Override
    public void pickaxeDraw(Position position) {
        TextGraphics textGraphics = this.screen.newTextGraphics();
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#C0C0C0"));
        textGraphics.putString(position.getX(), position.getY(), Character.toString(0x26CF));
    }
    @Override
    public void ladderDraw(Position position) {
        TextGraphics textGraphics = this.screen.newTextGraphics();
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#603030"));
        textGraphics.putString(position.getX(), position.getY(), Character.toString(0x210D));
    }
    @Override
    public void drawText(Position position, String content, String colorText, String colorBg, SGR style) {
        TextGraphics textGraphics = this.screen.newTextGraphics();
        textGraphics.setBackgroundColor(TextColor.Factory.fromString(colorBg));
        textGraphics.setForegroundColor(TextColor.Factory.fromString(colorText));
        textGraphics.putString(position.getX(), position.getY(), content, style);
    }
    @Override
    public void drawElementInterface(Position position, int codepoint, String color){
        TextGraphics textGraphics = this.screen.newTextGraphics();
        textGraphics.setForegroundColor(TextColor.Factory.fromString(color));
        textGraphics.putString(position.getX(), position.getY(), Character.toString(codepoint));
    }

    @Override
    public void clear() {
        this.screen.clear();
    }

    @Override
    public void refresh() throws IOException {
        this.screen.refresh();
    }

    @Override
    public void close() throws IOException {
        this.screen.close();
    }
    @Override
    public int getWidth() {
        return width;
    }
    @Override
    public int getHeight() {
        return height;
    }
}
