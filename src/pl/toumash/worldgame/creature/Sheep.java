package pl.toumash.worldgame.creature;

import pl.toumash.worldgame.GameWorld;

public class Sheep extends Animal {
    Sheep(GameWorld w, int x, int y) {
        super(w, x, y, 4, 4, DrawConfig.SHEEP);
    }
}
