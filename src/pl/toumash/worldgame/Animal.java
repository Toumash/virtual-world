package pl.toumash.worldgame;

import java.awt.*;

abstract class Animal extends Creature {
    private Color color;

    Animal(int x, int y, Color c) {
        super(x, y);
    }

    @Override
    public void draw(Graphics g, double scaleX, double scaleY) {
        g.setColor(color);
        g.fillRect((int) (getX() * scaleX), (int) (getY() * scaleY), (int) scaleX, (int) scaleY);
    }

    @Override
    public abstract void spread();

    @Override
    public abstract void collide(Creature c);
}
