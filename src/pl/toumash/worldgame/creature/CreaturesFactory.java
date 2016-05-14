package pl.toumash.worldgame.creature;

public class CreaturesFactory {
    public enum Creatures{
        wolf
    }

    public static Creature create(Creatures name, int x, int y){
        switch (name){
            case wolf:
                return new Wolf(x,y);
        }
        return null;
    }
}
