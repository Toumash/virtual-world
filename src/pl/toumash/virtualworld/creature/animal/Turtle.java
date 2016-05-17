package pl.toumash.virtualworld.creature.animal;

import pl.toumash.virtualworld.GameWorld;
import pl.toumash.virtualworld.creature.Animal;
import pl.toumash.virtualworld.creature.DrawConfig;

import java.util.Random;

public class Turtle extends Animal {

    public Turtle(GameWorld w, int x, int y) {
        super(w, x, y, 2, 1, DrawConfig.TURTLE);
    }

    @Override
    public void update() {
        Random r = new Random();
        int test = r.nextInt(4);
        if(test == 0){
            super.update();
        }
    }
}
