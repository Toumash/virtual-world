package pl.toumash.worldgame.creature;


import java.awt.*;

public abstract class Creature implements Drawable, Cloneable {
    private int x, y;

    Creature(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public abstract void draw(Graphics g, double scaleX, double scaleY);

    public abstract void spread();

    public abstract void collide(Creature c);

    @Override
    public Creature clone() {
        try {
            return (Creature) super.clone();
        } catch (Exception ignore) { return null;}
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
