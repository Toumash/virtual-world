package pl.toumash.virtualworld.creature.plant;


import pl.toumash.virtualworld.GameWorld;
import pl.toumash.virtualworld.creature.DrawConfig;

public class NightShade extends Plant {
    public NightShade(GameWorld gameWorld, int x, int y) {
        super(gameWorld, x, y, 99, DrawConfig.NIGHTSHADE);
    }
}
