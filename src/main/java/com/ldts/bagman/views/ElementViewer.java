package com.ldts.bagman.views;

import com.ldts.bagman.gui.GUI;

public interface ElementViewer<T> {
    void draw(T object, GUI lanternaGUI);

}
