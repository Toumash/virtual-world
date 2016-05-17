package pl.toumash.worldgame.creature;

import pl.toumash.worldgame.GameWorld;

public class Fox extends Animal {
    Fox(GameWorld w, int x, int y) {
        super(w, x, y, 3, 7, DrawConfig.FOX);
    }
}
