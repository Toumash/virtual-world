package pl.toumash.worldgame.creature.animal;

import pl.toumash.worldgame.Direction;
import pl.toumash.worldgame.GameWorld;
import pl.toumash.worldgame.creature.Animal;
import pl.toumash.worldgame.creature.DrawConfig;

public class Human extends Animal {

    Direction dir = null;

    public Human(GameWorld w, int x, int y) {
        super(w, x, y, 5, 4, DrawConfig.HUMAN);
    }

    @Override
    public void update()
    {
        move(dir);
    }

    public void setNextMove(Direction d){
        this.dir = d;
    }
}
