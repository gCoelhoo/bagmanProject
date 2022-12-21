package com.ldts.bagman.gui;

import com.googlecode.lanterna.SGR;
import com.ldts.bagman.models.Position;

import java.io.IOException;

public interface GUI {

    int getWidth();

    int getHeight();

    enum ACTIONS {UP, DOWN, RIGHT, LEFT, USE, NONE, QUIT}
    ACTIONS getNextAction() throws IOException;

    void wallDraw(Position position);
    void playerDraw(Position position);
    void policemanDraw(Position position);
    void coinBagDraw(Position position);
    void wheelbarrowDraw(Position position);
    void pickaxeDraw(Position position);
    void ladderDraw(Position position);

    void drawText(Position position, String content, String colorText, String colorBg, SGR style);

    void drawElementInterface(Position position, int codepoint, String color);

    void clear();
    void refresh() throws IOException;
    void close() throws IOException;
}
