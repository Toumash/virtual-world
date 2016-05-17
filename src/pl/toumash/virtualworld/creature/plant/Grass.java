package pl.toumash.virtualworld.creature.plant;


import pl.toumash.virtualworld.GameWorld;
import pl.toumash.virtualworld.creature.DrawConfig;

public class Grass extends Plant {
    public Grass(GameWorld gameWorld, int x, int y) {
        super(gameWorld, x, y, 0, DrawConfig.GRASS);
    }
}
