package pl.toumash.worldgame.creature;

import pl.toumash.worldgame.GameWorld;

import java.awt.*;

public class Wolf extends Animal {
    public Wolf(GameWorld w, int x, int y) {
        super(w,x, y, Color.white);
    }

    @Override
    public void spread() {

    }

    @Override
    public void collide(Creature c) {

    }
}
