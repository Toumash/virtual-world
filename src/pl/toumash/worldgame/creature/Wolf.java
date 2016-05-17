package pl.toumash.worldgame.creature;

import pl.toumash.worldgame.GameWorld;

public class Wolf extends Animal {

    public Wolf(GameWorld w, int x, int y) {
        super(w, x, y, 9, 5, DrawConfig.WOLF);
    }

}
