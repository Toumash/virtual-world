package pl.toumash.worldgame.creature.plant;


import pl.toumash.worldgame.GameWorld;
import pl.toumash.worldgame.creature.DrawConfig;

public class Guarana extends Plant {
    public Guarana(GameWorld gameWorld, int x, int y) {
        super(gameWorld, x, y, 0, DrawConfig.GUARANA);
    }
}
