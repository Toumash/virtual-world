package pl.toumash.worldgame.creature;

import pl.toumash.worldgame.GameWorld;

import java.awt.*;
import java.util.Random;

public class Human extends Animal {

    public Human(GameWorld w, int x, int y) {
        super(w, x, y, 5, 4, Color.blue);
    }

    @Override
    public void update(){
        // DO NOTHING
        // GameView Handles human Movement
    }
}
