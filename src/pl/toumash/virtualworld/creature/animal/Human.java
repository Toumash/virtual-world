package pl.toumash.virtualworld.creature.animal;

import pl.toumash.virtualworld.Direction;
import pl.toumash.virtualworld.GameWorld;
import pl.toumash.virtualworld.creature.Animal;
import pl.toumash.virtualworld.creature.DrawConfig;

public class Human extends Animal {

    Direction dir = null;
    int basicScrength;
    int cooldown = 0;

    public Human(GameWorld w, int x, int y) {
        super(w, x, y, 5, 4, DrawConfig.HUMAN);
        basicScrength = getStrength();
    }

    @Override
    public void update() {
        move(dir);
        if (cooldown > 0) {
            cooldown--;
            if(cooldown == 0){
                improveStrength(basicScrength -getStrength());
            }
            gameWorld.addEvent("HUMAN: SA COOLDOWN: " + cooldown + " turns");
            if (getStrength() > basicScrength) {
                improveStrength(-1); // FIXME: yup, thats good, subtracting strength
                gameWorld.addEvent("HUMAN: Strength=" + getStrength());
            }
        }
    }

    public void setNextMove(Direction d) {
        this.dir = d;
    }

    public void executeSuperAbility() {
        if (getStrength() < 10) {
            cooldown = 5;
            improveStrength(10 - getStrength());
        }
    }


    @Override
    public void improveStrength(int delta) {
        basicScrength += delta;
        super.improveStrength(delta);
    }
}
