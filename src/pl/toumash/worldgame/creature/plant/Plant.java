package pl.toumash.worldgame.creature.plant;


import pl.toumash.worldgame.GameWorld;
import pl.toumash.worldgame.creature.Creature;

import java.awt.*;

public class Plant extends Creature {

    public Plant(GameWorld gameWorld, int x, int y, int strength, Color c) {
        super(gameWorld, x, y, strength, 0, c);
    }

    @Override
    public void update() {
    }

    @Override
    public void collide(Creature c) {
    }
}
