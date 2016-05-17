package pl.toumash.worldgame.creature.plant;


import pl.toumash.worldgame.Direction;
import pl.toumash.worldgame.GameWorld;
import pl.toumash.worldgame.creature.Creature;

import java.awt.*;
import java.util.Random;

public class Plant extends Creature {

    public Plant(GameWorld gameWorld, int x, int y, int strength, Color c) {
        super(gameWorld, x, y, strength, 0, c);
    }

    @Override
    public void update() {
        Random r = new Random();
        int test = r.nextInt(100);
        if (test > 80) {
            spread();
        }
    }

    public void spread() {
        Direction dir = null;
        for (Direction d : Direction.values()) {
            if (!gameWorld.isOccupied(getX(), getY(), d)) {
                dir = d;
            }
        }
        if (dir != null) {
            spawn(dir);
        }
    }

}
