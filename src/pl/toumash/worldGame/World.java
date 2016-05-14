package pl.toumash.worldgame;

import java.util.ArrayList;

class World {
    private int width, height;
    ArrayList<Creature> creatures = new ArrayList<>();

    World(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void randominit(){
        creatures.add(new Wolf(5,5));
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

}
