package pl.toumash.worldgame.creature.plant;


import pl.toumash.worldgame.GameWorld;
import pl.toumash.worldgame.creature.DrawConfig;

public class NightShade extends Plant {
    public NightShade(GameWorld gameWorld, int x, int y) {
        super(gameWorld, x, y, 99, DrawConfig.NIGHTSHADE);
    }
}
