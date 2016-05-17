package pl.toumash.virtualworld.creature.animal;

import pl.toumash.virtualworld.GameWorld;
import pl.toumash.virtualworld.creature.Animal;
import pl.toumash.virtualworld.creature.DrawConfig;

public class Sheep extends Animal {
    public  Sheep(GameWorld w, int x, int y) {
        super(w, x, y, 4, 4, DrawConfig.SHEEP);
    }
}
