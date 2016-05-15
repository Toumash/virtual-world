package pl.toumash.worldgame;

import pl.toumash.worldgame.creature.Creature;
import pl.toumash.worldgame.creature.Wolf;

import java.awt.*;
import java.util.ArrayList;

class World {
    private ArrayList<Creature> creatures = new ArrayList<>();
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

    public void spawn(Creature creature) {
        this.creatures.add(creature);
    }

    public void draw(Graphics g, double scaleX, double scaleY) {
        for (Creature c : creatures) {
            c.draw(g, scaleX, scaleY);
        }
    }

    public boolean isOccupied(int x, int y) {
                for(Creature c : creatures){
                    if(c.getX() == x && c.getY() ==y){
                        return true;
                    }
                }
        return false;
    }
}
