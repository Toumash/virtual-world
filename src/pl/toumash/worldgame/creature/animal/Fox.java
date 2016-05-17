package pl.toumash.worldgame.creature.animal;

import pl.toumash.worldgame.Direction;
import pl.toumash.worldgame.GameWorld;
import pl.toumash.worldgame.creature.Animal;
import pl.toumash.worldgame.creature.DrawConfig;

import java.util.Random;

public class Fox extends Animal {
    public Fox(GameWorld w, int x, int y) {
        super(w, x, y, 3, 7, DrawConfig.FOX);
    }

    @Override
    public void update() {
        Random r = new Random();
        int d = r.nextInt(Direction.values().length);
        if (!canMove(Direction.values()[d]) && isStrongerThan(Direction.values()[d])) {
            d = 0;
            do {
                d++;
            } while (!canMove(Direction.values()[d]));
        }
        move(Direction.values()[d]);
    }
}
