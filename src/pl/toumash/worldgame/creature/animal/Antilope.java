package pl.toumash.worldgame.creature.animal;

import pl.toumash.worldgame.Direction;
import pl.toumash.worldgame.GameWorld;
import pl.toumash.worldgame.creature.Animal;
import pl.toumash.worldgame.creature.DrawConfig;

import java.util.Random;

public class Antilope extends Animal {

    public Antilope(GameWorld w, int x, int y) {
        super(w, x, y, 2, 1, DrawConfig.ANTILOPE);
    }

    @Override
    public void update() {
        Random r = new Random();
        int firstMove = r.nextInt(Direction.values().length);
        if (!canMove(Direction.values()[firstMove])) {
            firstMove = 0;
            do {
                firstMove++;
            } while (firstMove < Direction.values().length && !canMove(Direction.values()[firstMove]));
        }
        move(Direction.values()[firstMove]);

        int secondMove = r.nextInt(Direction.values().length);
        if (!canMove(Direction.values()[secondMove]) || isBackTrack(Direction.values()[firstMove], Direction.values()[secondMove])) {
            secondMove = 0;
            do {
                if(secondMove < Direction.values().length) {
                    secondMove++;
                }
            }
            while (secondMove < Direction.values().length
                    && (!canMove(Direction.values()[secondMove])
                    || isBackTrack(Direction.values()[firstMove], Direction.values()[secondMove])));
        }
        if (!isBackTrack(Direction.values()[firstMove], Direction.values()[secondMove])) {
            move(Direction.values()[secondMove]);
        }
    }

    public void flee() {
        Direction d = null;
        for (int i = 0; i < Direction.values().length; i++) {
            d = Direction.values()[i];
            if (canMove(d) && !gameWorld.isOccupied(getX(), getY(), d)) {
                move(d);
                return;
            }
        }
    }

    public boolean isBackTrack(Direction d, Direction d2) {
        return d == Direction.down && d2 == Direction.up
                || d == Direction.up && d2 == Direction.down
                || d == Direction.left && d2 == Direction.right
                || d == Direction.right && d2 == Direction.left;
    }
}

