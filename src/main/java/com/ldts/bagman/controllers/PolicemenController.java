package com.ldts.bagman.controllers;

import com.ldts.bagman.Game;
import com.ldts.bagman.gui.GUI;
import com.ldts.bagman.models.Elements.Element;
import com.ldts.bagman.models.Elements.Player;
import com.ldts.bagman.models.Elements.Policeman;
import com.ldts.bagman.models.Elements.Object;
import com.ldts.bagman.models.MapModels.Ladder;
import com.ldts.bagman.models.MapModels.Map;
import com.ldts.bagman.models.MapModels.Wall;
import com.ldts.bagman.models.Position;
import com.ldts.bagman.sounds.DamageEffect;
import com.ldts.bagman.sounds.PickaxeEffect;
import com.ldts.bagman.sounds.SoundEffect;

import java.util.List;

public class PolicemenController extends ElementController{
    private long movementTimer = 0;
    private Game game;
    private SoundEffect pickaxeSound = new PickaxeEffect();
    private SoundEffect damageSound = new DamageEffect();


    private Element.FLAG isOnLadderOrGround(Policeman policeman){
        Position policePos = policeman.getPosition();

        for(Ladder ladder : game.getState().getGameMap().getLadders()){
            Position ladderPos = ladder.getPosition();

            if(ladderPos.equals(policePos))
                return Element.FLAG.ONLADDER;
        }

        for(Wall wall : game.getState().getGameMap().getWalls()){
            Position wallPos = wall.getPosition();

            if((wallPos.getY() == policePos.getY()+1) && (wallPos.getX() == policePos.getX()))
                return Element.FLAG.ONGROUND;
        }

        return Element.FLAG.ONAIR;
    }
    public boolean canMove(Position position){
        for(Wall wall : game.getState().getGameMap().getWalls()){
            Position wallPos = wall.getPosition();

            if(wallPos.equals(position))
                return false;
        }
        return true;
    }
    private void move(Policeman policeman, Player player){
        if (policeman.isKO()) {
            policeman.setKo_timer(policeman.getKo_timer() + 1);
            if (policeman.getKo_timer() < 15) {
                return;
            }
            policeman.setKo(false);
        }
        Element.FLAG flag = isOnLadderOrGround(policeman);
        policeman.setCurrent_direction(policeman.getPosition().posRandomizer(flag, canMoveLeft(policeman), canMoveRight(policeman), canMoveUp(policeman), canMoveDown(policeman), policeman, player));
        Position new_pos;
        if (policeman.getCurrent_direction() == Element.DIRECTION.DOWN) {
            new_pos = new Position(policeman.getPosition().getX(), policeman.getPosition().getY() + 1);
        }
        else if (policeman.getCurrent_direction() == Element.DIRECTION.UP) {
            new_pos = new Position(policeman.getPosition().getX(), policeman.getPosition().getY() - 1);
        }
        else if (policeman.getCurrent_direction() == Element.DIRECTION.RIGHT) {
            new_pos = new Position(policeman.getPosition().getX() + 1, policeman.getPosition().getY());
        }
        else {
            new_pos = new Position(policeman.getPosition().getX() - 1, policeman.getPosition().getY());
        }
        policeman.setPosition(new_pos);
    }
    private boolean canMoveLeft(Policeman policeman) {
        return canMove(new Position(policeman.getPosition().getX() - 1, policeman.getPosition().getY()));
    }
    private boolean canMoveRight(Policeman policeman) {
        return canMove(new Position(policeman.getPosition().getX() + 1, policeman.getPosition().getY()));
    }
    private boolean canMoveUp(Policeman policeman) {
        return canMove(new Position(policeman.getPosition().getX(), policeman.getPosition().getY() - 1));
    }
    private boolean canMoveDown(Policeman policeman) {
        return canMove(new Position(policeman.getPosition().getX(), policeman.getPosition().getY() + 1));
    }

    @Override
    public void update(Game game, GUI.ACTIONS action, long time) {
        this.game = game;
        List<Policeman> policemen = game.getState().getGameMap().getPolicemen();
        Player player = game.getState().getGameMap().getPlayer();

        if(time - movementTimer > 500){
            for(Policeman policeman : policemen) {
                if (policeman.getPosition().distance(player.getPosition()) <= 2) {
                    if (player.isCarryingObject()) {
                        if (player.getObject().type() == Object.TYPE.PICKAXE) {
                            policeman.setKo(true);
                            player.setPoints(1000);
                            pickaxeSound.play();
                            Object pickaxe = player.dropObject();
                            game.getState().getGameMap().getObjects().remove(pickaxe);
                        }
                    }
                    else if (!(policeman.isKO())) {
                        player.setKo(true);
                        damageSound.play();
                        player.setDamage();
                        player.returnToInitialPos();
                        policeman.returnToInitialPos();
                    }
                }
                move(policeman, game.getState().getGameMap().getPlayer());
            }
            this.movementTimer = time;
        }
    }
}
