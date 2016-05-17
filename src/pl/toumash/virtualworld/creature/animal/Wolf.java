package pl.toumash.virtualworld.creature.animal;

import pl.toumash.virtualworld.GameWorld;
import pl.toumash.virtualworld.creature.Animal;
import pl.toumash.virtualworld.creature.DrawConfig;

public class Wolf extends Animal {

    public Wolf(GameWorld w, int x, int y) {
        super(w, x, y, 9, 5, DrawConfig.WOLF);
    }

}
