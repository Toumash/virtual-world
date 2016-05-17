package pl.toumash.worldgame.creature.plant;


import pl.toumash.worldgame.GameWorld;
import pl.toumash.worldgame.creature.DrawConfig;

public class Dandelion extends Plant {
    public Dandelion(GameWorld gameWorld, int x, int y) {
        super(gameWorld, x, y, 0, DrawConfig.DANDELION);
    }
    @Override
    public void update(){
        super.update();
        super.update();
        super.update();
    }
}
