package com.ldts.bagman;

import com.ldts.bagman.gui.LanternaGUI;
import com.ldts.bagman.models.MapModels.MapCreator;
import com.ldts.bagman.models.Menu.Menu;
import com.ldts.bagman.sounds.SoundEffect;
import com.ldts.bagman.sounds.Soundtrack;
import com.ldts.bagman.states.GameState;
import com.ldts.bagman.states.MenuState;
import com.ldts.bagman.states.State;

import java.io.IOException;

public class Game {
    private final LanternaGUI lanternaGUI;
    private State state;
    private SoundEffect soundtrack = new Soundtrack();

    public Game(State state, LanternaGUI lanternaGUI) {
        this.state = state;
        this.lanternaGUI = lanternaGUI;
    }

    public Game() throws IOException {
        this.lanternaGUI = new LanternaGUI(40,20);
//        this.state = new GameState(new MapCreator().createMap());
        this.state = new MenuState(new Menu());
    }

    public static void main(String[] args) throws IOException {
        new Game().run();
    }

    public void setState(State state) {
        this.state = state;
    }
    public State getState() { return this.state; }

    void run() throws IOException {
        int frameTime = 100;
        soundtrack.loop();

        while(this.state != null){
            long startTime = System.currentTimeMillis();

            state.update(this, this.lanternaGUI, startTime);

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            try{
                if(sleepTime > 0) Thread.sleep(sleepTime);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        lanternaGUI.close();
    }
}
