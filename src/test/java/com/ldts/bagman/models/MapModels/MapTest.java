package com.ldts.bagman.models.MapModels;

import com.ldts.bagman.models.Elements.CoinBag;
import com.ldts.bagman.models.Elements.Object;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class MapTest {
    @Test
    public void mapInstance(){
        Map map = Mockito.mock(Map.class);
        List<Wall> walls = new ArrayList<>();
        List<Object> coinBags = new ArrayList<>();
        walls.add(new Wall(0,0));
        walls.add(new Wall(1,1));
        coinBags.add(new CoinBag(2,2));

        map.setWalls(walls);
        map.setObjects(coinBags);

        Assertions.assertNotNull(map.getWalls());
        Assertions.assertNotNull(map.getObjects());
        Assertions.assertNull(map.getPlayer());
    }
}
