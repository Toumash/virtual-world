package pl.toumash.virtualworld.creature.plant;


import pl.toumash.virtualworld.Direction;
import pl.toumash.virtualworld.GameWorld;
import pl.toumash.virtualworld.creature.Creature;

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
