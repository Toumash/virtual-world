package pl.toumash.worldgame.creature.animal;

import pl.toumash.worldgame.GameWorld;
import pl.toumash.worldgame.creature.Animal;
import pl.toumash.worldgame.creature.DrawConfig;

public class Wolf extends Animal {

    public Wolf(GameWorld w, int x, int y) {
        super(w, x, y, 9, 5, DrawConfig.WOLF);
    }

}
