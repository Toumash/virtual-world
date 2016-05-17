package pl.toumash.worldgame.creature;


import pl.toumash.worldgame.Direction;
import pl.toumash.worldgame.Drawable;
import pl.toumash.worldgame.GameWorld;
import pl.toumash.worldgame.Position;

import java.awt.*;

public abstract class Creature implements Drawable, Cloneable {
    protected GameWorld gameWorld;
    private Position coords;
    int strength;
    int priority;

    public Creature(GameWorld gameWorld, int x, int y, int strength, int priority) {
        this.gameWorld = gameWorld;
        this.coords = new Position(x,y);
        this.strength = strength;
        this.priority = priority;
    }

    @Override
    public abstract void draw(Graphics g, double scaleX, double scaleY);

    protected Direction findFreeSpace() {
        for (Direction d : Direction.values()) {
            if (!gameWorld.isOccupied(getX(), getY(), d)) {
                return d;
            }
        }
        return null;
    }

    protected void spawn(Direction d) {
        gameWorld.spawn(this.clone().move(d));
    }

    public abstract void collide(Creature c);

    public boolean Move(Direction d) {
        if (canMove(d)) {
            move(d);
            return true;
        }
        return false;
    }

    private Creature move(Direction d) {
        coords.move(d);
        return this;
    }

    private boolean canMove(Direction d) {
        // deltas
        int dX = 0;
        int dY = 0;
        switch (d) {
            case up:
                dY -= 1;
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
        return gameWorld.inBounds(coords.x + dX, coords.y + dY);
    }

    @Override
    public Creature clone() {
        try {
            return (Creature) super.clone();
        } catch (Exception ignore) {
            return null;
        }
    }

    public int getX() {
        return coords.x;
    }

    public void setX(int x) {
        this.coords.x = x;
    }

    public int getY() {
        return coords.y;
    }

    public void setY(int y) {
        this.coords.y = y;
    }

}