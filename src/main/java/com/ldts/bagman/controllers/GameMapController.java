package com.ldts.bagman.controllers;

import com.ldts.bagman.Game;
import com.ldts.bagman.gui.GUI;
import com.ldts.bagman.models.Elements.Object;
import com.ldts.bagman.models.Elements.Player;
import com.ldts.bagman.models.Elements.Policeman;
import com.ldts.bagman.models.MapModels.Ladder;
import com.ldts.bagman.models.MapModels.Wall;
import com.ldts.bagman.models.Menu.Menu;
import com.ldts.bagman.models.Position;
import com.ldts.bagman.states.MenuState;

public class GameMapController extends Controller{
    private final PlayerController player_controller = new PlayerController();
    private final PolicemenController policemenController = new PolicemenController();
    private final WheelbarrowController wheelbarrowController = new WheelbarrowController();
    private final CoinBagController coinBagController = new CoinBagController();
    private final PickaxeController pickaxeController = new PickaxeController();

    public void update(Game game, GUI.ACTIONS action, long time) {
        int lives = game.getState().getGameMap().getPlayer().getLives();
        if(action == GUI.ACTIONS.QUIT ||
                lives == 0) game.setState(new MenuState(new Menu()));
        else {
            Player pl = game.getState().getGameMap().getPlayer();
            if (pl.getPosition().getX() < 0) {
                for (Object o : game.getState().getGameMap().getObjects()) {
                    o.setPosition(new Position(o.getPosition().getX() + 36, o.getPosition().getY()));
                }
                for (Wall w : game.getState().getGameMap().getWalls()) {
                    w.setPosition(new Position(w.getPosition().getX() + 36, w.getPosition().getY()));
                }
                for (Ladder l : game.getState().getGameMap().getLadders()) {
                    l.setPosition(new Position(l.getPosition().getX() + 36, l.getPosition().getY()));
                }
                for (Policeman p : game.getState().getGameMap().getPolicemen()) {
                    p.setPosition(new Position(p.getPosition().getX() + 36, p.getPosition().getY()));
                    p.setInitialPos(new Position(p.getInitialPos().getX() + 36, p.getInitialPos().getY()));
                }
                pl.setPosition(new Position(pl.getPosition().getX() + 36, pl.getPosition().getY()));
                pl.setInitialPos(new Position(pl.getInitialPos().getX() + 36, pl.getInitialPos().getY()));
            }
            else if (pl.getPosition().getX() > 35) {
                for (Object o : game.getState().getGameMap().getObjects()) {
                    o.setPosition(new Position(o.getPosition().getX() - 36, o.getPosition().getY()));
                }
                for (Wall w : game.getState().getGameMap().getWalls()) {
                    w.setPosition(new Position(w.getPosition().getX() - 36, w.getPosition().getY()));
                }
                for (Ladder l : game.getState().getGameMap().getLadders()) {
                    l.setPosition(new Position(l.getPosition().getX() - 36, l.getPosition().getY()));
                }
                for (Policeman p : game.getState().getGameMap().getPolicemen()) {
                    p.setPosition(new Position(p.getPosition().getX() - 36, p.getPosition().getY()));
                    p.setInitialPos(new Position(p.getInitialPos().getX() - 36, p.getInitialPos().getY()));
                }
                pl.setPosition(new Position(pl.getPosition().getX() - 36, pl.getPosition().getY()));
                pl.setInitialPos(new Position(pl.getInitialPos().getX() - 36, pl.getInitialPos().getY()));
            }
            player_controller.update(game, action, time);
            policemenController.update(game, action, time);
            coinBagController.update(game, action, time);
            wheelbarrowController.update(game, action, time);
            pickaxeController.update(game, action, time);
        }
    }
}
