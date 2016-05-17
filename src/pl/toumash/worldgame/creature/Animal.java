package pl.toumash.worldgame.creature;


import pl.toumash.worldgame.GameWorld;

import java.awt.*;

public abstract class Animal extends Creature {
    private Color color;

    Animal(GameWorld w, int x, int y, Color c) {
        super(w,x, y);
        this.color = c;
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
