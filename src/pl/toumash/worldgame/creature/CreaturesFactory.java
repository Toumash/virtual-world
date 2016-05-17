package pl.toumash.worldgame.creature;

import pl.toumash.worldgame.GameWorld;
import pl.toumash.worldgame.creature.animal.Wolf;

public class CreaturesFactory {
    public enum Creatures{
        wolf
    }

    public static Creature create(GameWorld w, Creatures name, int x, int y){
        switch (name){
            case wolf:
                return new Wolf(w,x,y);
        }
        return null;
    }
}
