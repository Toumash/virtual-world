package pl.toumash.worldgame;

import pl.toumash.worldgame.creature.Creature;
import pl.toumash.worldgame.creature.Human;
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
        Human h = new Human(this, getWidth() / 2, getHeight() / 2);
        creatures.add(h);
        this.player = h;
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

    public boolean isOccupied(int x, int y, Direction d) {
        int dX = 0, dY = 0;
        // TODO: remove duplicate code
        switch (d) {
            case up:
                dY = -1;
                break;
            case down:
                dY = +1;
                break;
            case left:
                dX = -1;
                break;
            case right:
                dX = +1;
                break;
        }
        for (Creature c : creatures) {
            if (c.getX() + dX == x && c.getY() + dY == y) {
                return true;
            }
        }
        return false;
    }

    public void update() {
        for (Creature c : creatures) {
            if (c.isAlive()) {
                c.update();
            }
        }
    }

    public void playerMove(Direction dir) {
        if (player != null) {
            if (player.canMove(dir))
                player.move(dir);
        }
    }
}
