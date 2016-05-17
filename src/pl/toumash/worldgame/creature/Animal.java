package pl.toumash.worldgame.creature;


import pl.toumash.worldgame.Direction;
import pl.toumash.worldgame.GameWorld;

import java.awt.*;

public abstract class Animal extends Creature {
    private Color color;

    Animal(GameWorld w, int x, int y, int strength, int priority, Color c) {
        super(w, x, y, strength, priority);
        this.color = c;
    }

    @Override
    public void draw(Graphics g, double scaleX, double scaleY) {
        g.setColor(color);
        g.fillRect((int) (getX() * scaleX), (int) (getY() * scaleY), (int) scaleX, (int) scaleY);
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
    public abstract void collide(Creature c);
}
