package pl.toumash.worldgame.creature;

import pl.toumash.worldgame.GameWorld;

import java.awt.*;
import java.util.Random;

public class Turtle extends Animal {

    public Turtle(GameWorld w, int x, int y) {
        super(w, x, y, 2, 1, Color.white);
    }

    @Override
    public void update() {
        Random r = new Random();
        int test = r.nextInt(4);
        if(test == 0){
            super.update();
        }
    }

    @Override
    public void collide(Creature c) {

    }
}
