package pl.toumash.worldgame.creature.animal;

import pl.toumash.worldgame.GameWorld;
import pl.toumash.worldgame.creature.Animal;
import pl.toumash.worldgame.creature.DrawConfig;

public class Sheep extends Animal {
    Sheep(GameWorld w, int x, int y) {
        super(w, x, y, 4, 4, DrawConfig.SHEEP);
    }
}
