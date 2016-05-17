package pl.toumash.virtualworld.creature.plant;


import pl.toumash.virtualworld.GameWorld;
import pl.toumash.virtualworld.creature.DrawConfig;

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
