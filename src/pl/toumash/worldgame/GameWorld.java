package pl.toumash.worldgame;

import pl.toumash.worldgame.creature.Creature;
import pl.toumash.worldgame.creature.Wolf;

import java.awt.*;
import java.util.ArrayList;

public class GameWorld {
    private ArrayList<Creature> creatures = new ArrayList<>();
    private Creature player;
    private int width, height;

    GameWorld(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void randominit() {
        creatures.add(new Wolf(this, 5, 5));
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
        for (Creature c : creatures) {
            if (c.getX() == x && c.getY() == y) {
                return true;
            }
        }
        return false;
    }

    public boolean inBounds(int x, int y) {
        return (x >= 0 && x < getWidth()) && (y >= 0 && y < getHeight());
    }
}
