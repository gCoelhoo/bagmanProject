package com.ldts.bagman.models.MapModels;

import com.ldts.bagman.models.Elements.*;
import com.ldts.bagman.models.Elements.Object;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapCreator {
    private List<String> lines = new ArrayList<>();

    public MapCreator(){
        try {
            FileReader fileReader = new FileReader("src/main/resources/Maps/Map1.map");
            BufferedReader reader = new BufferedReader(fileReader);
            this.lines = readMap(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> readMap(BufferedReader reader) throws IOException {
        List<String> lines = new ArrayList<>();

        String line;
        while((line = reader.readLine()) != null)
            lines.add(line);

        return lines;
    }

    private int getHeight(){ return lines.size(); }
    private int getWidth(){
        int maxWidth = 0;
        for(String line: lines)
            maxWidth = Math.max(maxWidth, line.length());
        return maxWidth;
    }

    public Map createMap(){
        Map map = new Map(getWidth(), getHeight());

        map.setPlayer(playerCreator());
        map.setPolicemen(policemanCreator());
        map.setLadders(ladderCreator());
        map.setWalls(wallCreator());
        map.setObjects(objectCreator());

        return map;
    }

    private List<Object> objectCreator() {
        List<CoinBag> coinbags = coinBagCreator();
        Wheelbarrow w = wheelbarrowCreator();
        List<Pickaxe> pickaxes = pickaxeCreator();

        List<Object> res = new ArrayList<>();
        for (CoinBag cb : coinbags) {
            res.add(cb);
        }
        for (Pickaxe p : pickaxes) {
            res.add(p);
        }
        res.add(w);
        return res;
    }

    private List<Pickaxe> pickaxeCreator() {
        List<Pickaxe> pickaxes = new ArrayList<>();

        for(int lat = 0; lat < lines.size(); ++lat){
            String line = lines.get(lat);
            for(int lon = 0; lon < line.length(); ++lon)
                if(line.charAt(lon) == 'T') pickaxes.add(new Pickaxe(lon, lat));
        }
        return pickaxes;
    }

    private Player playerCreator(){
        for(int lat = 0; lat < lines.size(); ++lat){
            String line = lines.get(lat);
            for(int lon = 0; lon < line.length(); ++lon)
                if(line.charAt(lon) == '@') return new Player(lon,lat);
        }
        return null;
    }
    private List<Policeman> policemanCreator(){
        List<Policeman> policemen = new ArrayList<>();

        for(int lat = 0; lat < lines.size(); ++lat){
            String line = lines.get(lat);
            for(int lon = 0; lon < line.length(); ++lon)
                if(line.charAt(lon) == 'P') policemen.add(new Policeman(lon,lat));
        }
        return policemen;
    }
    private List<CoinBag> coinBagCreator(){
        List<CoinBag> coinBags = new ArrayList<>();

        for(int lat = 0; lat < lines.size(); ++lat){
            String line = lines.get(lat);
            for(int lon = 0; lon < line.length(); ++lon)
                if(line.charAt(lon) == '$') coinBags.add(new CoinBag(lon,lat));
        }
        return coinBags;
    }
    private List<Ladder> ladderCreator(){
        List<Ladder> ladders = new ArrayList<>();

        for(int lat = 0; lat < lines.size(); ++lat){
            String line = lines.get(lat);
            for(int lon = 0; lon < line.length(); ++lon)
                if(line.charAt(lon) == '+') ladders.add(new Ladder(lon,lat));
        }
        return ladders;
    }
    private Wheelbarrow wheelbarrowCreator(){
        for(int lat = 0; lat < lines.size(); ++lat){
            String line = lines.get(lat);
            for(int lon = 0; lon < line.length(); ++lon)
                if(line.charAt(lon) == '%') return new Wheelbarrow(lon,lat);
        }
        return null;
    }
    private List<Wall> wallCreator(){
        List<Wall> walls = new ArrayList<>();

        for(int lat = 0; lat < lines.size(); ++lat){
            String line = lines.get(lat);
            for(int lon = 0; lon < line.length(); ++lon)
                if(line.charAt(lon) == '#') walls.add(new Wall(lon,lat));
        }
        return walls;
    }

}
