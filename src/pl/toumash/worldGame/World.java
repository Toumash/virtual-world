package pl.toumash.worldgame;

import pl.toumash.worldgame.creature.Creature;
import pl.toumash.worldgame.creature.Wolf;

import java.util.ArrayList;

class World {
    ArrayList<Creature> creatures = new ArrayList<>();
    private int width, height;

    World(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void randominit() {
        creatures.add(new Wolf(5, 5));
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

}
