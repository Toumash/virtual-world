package pl.toumash.worldgame.creature;


import pl.toumash.worldgame.Direction;
import pl.toumash.worldgame.Drawable;
import pl.toumash.worldgame.GameWorld;
import pl.toumash.worldgame.Position;

import java.awt.*;
import java.io.Serializable;

public abstract class Creature implements Drawable, Cloneable,Serializable {
    protected GameWorld gameWorld;
    int strength;
    int priority;
    boolean alive = true;
    private Position coords;
    private Color color;
    private Position prevCoords;

    public Creature(GameWorld gameWorld, int x, int y, int strength, int priority, Color c) {
        this.gameWorld = gameWorld;
        this.coords = new Position(x, y);
        this.strength = strength;
        this.priority = priority;
        this.color = c;
        this.prevCoords = new Position(x, y);
    }

    public abstract void update();

    protected Direction findFreeSpace() {
        for (Direction d : Direction.values()) {
            if (!gameWorld.isOccupied(getX(), getY(), d)) {
                return d;
            }
        }
        return null;
    }

    public void collide(Creature c) {
        Creature attacker = GameWorld.whoAttacks(this, 1, c, 0);
        this.gameWorld.kill(attacker, attacker == this ? c : this);
    }

    protected void spawn(Direction d) {
        gameWorld.spawn(this.clone().move(d));
    }

    public void kill() {
        alive = false;
    }

    public boolean isAlive() {
        return this.alive;
    }

    public boolean Move(Direction d) {
        if (canMove(d)) {
            move(d);
            return true;
        }
        return false;
    }

    public Creature move(Direction d) {
        this.prevCoords.setX(getX());
        this.prevCoords.setY(getY());
        if(d != null) {
            coords.move(d);
        }
        return this;
    }

    public boolean isStrongerThan(Creature c) {
        if (c == null) {
            return true;
        }
        return this.strength > c.strength;
    }

    public boolean isStrongerThan(Direction d) {
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
        return isStrongerThan(gameWorld.getCreature(getX() + dX, getY() + dY));
    }

    @Override
    public void draw(Graphics g, double scaleX, double scaleY) {
        g.setColor(color);
        g.fillRect((int) (getX() * scaleX), (int) (getY() * scaleY), (int) scaleX, (int) scaleY);
    }

    public boolean canMove(Direction d) {
        if(d == null){
            return true;
        }
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
        return gameWorld.inBounds(coords.getX() + dX, coords.getY() + dY);
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
        return coords.getX();
    }

    public void setX(int x) {
        this.prevCoords.setX(getX());
        this.coords.setX(x);
    }

    public int getY() {
        return coords.getY();
    }

    public void setY(int y) {
        this.prevCoords.setY(getY());
        this.coords.setY(y);

    }

    public int getStrength() {
        return strength;
    }

    public void improveStrength(int delta){
        this.strength += delta;
    }

    public int getPriority() {
        return priority;
    }

    public void cancelMove() {
        setY(prevCoords.getY());
        setX(prevCoords.getX());
    }
}