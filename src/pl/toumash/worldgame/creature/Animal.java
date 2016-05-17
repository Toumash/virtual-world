package pl.toumash.worldgame.creature;


import pl.toumash.worldgame.Direction;
import pl.toumash.worldgame.GameWorld;

import java.awt.*;
import java.util.Random;

public abstract class Animal extends Creature {

    protected Animal(GameWorld w, int x, int y, int strength, int priority, Color c) {
        super(w, x, y, strength, priority, c);
    }


    public void giveBirth() {
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

    @Override
    public void update() {
        Random r = new Random();
        int d = r.nextInt(Direction.values().length);
        if (!canMove(Direction.values()[d])) {
            d = 0;
            do {
                d++;
            } while (!canMove(Direction.values()[d]));
        }
        move(Direction.values()[d]);
    }


    @Override
    public void collide(Creature c) {
        // FIXME
    }
}
