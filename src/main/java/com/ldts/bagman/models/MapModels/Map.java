package com.ldts.bagman.models.MapModels;

import com.ldts.bagman.models.Elements.Player;
import com.ldts.bagman.models.Elements.Policeman;
import com.ldts.bagman.models.Elements.Object;

import java.util.List;

public class Map {
    private final int width;
    private final int height;

    private Player player;
    private List<Policeman> policemen;
    private List<Object> objects;
    private List<Wall> walls;
    private List<Ladder> ladders;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }

    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Policeman> getPolicemen() {
        return policemen;
    }
    public void setPolicemen(List<Policeman> policemen) {
        this.policemen = policemen;
    }

    public List<Wall> getWalls() {
        return walls;
    }
    public void setWalls(List<Wall> walls) {
        this.walls = walls;
    }

    public List<Ladder> getLadders() {
        return ladders;
    }
    public void setLadders(List<Ladder> ladders) {
        this.ladders = ladders;
    }

    public List<Object> getObjects() {
        return objects;
    }

    public void setObjects(List<Object> objects) {
        this.objects = objects;
    }
}
