package pl.toumash.virtualworld.creature;

import pl.toumash.virtualworld.GameWorld;
import pl.toumash.virtualworld.creature.animal.*;
import pl.toumash.virtualworld.creature.plant.Dandelion;
import pl.toumash.virtualworld.creature.plant.Grass;
import pl.toumash.virtualworld.creature.plant.Guarana;
import pl.toumash.virtualworld.creature.plant.NightShade;

public class CreaturesFactory {
    public static Creature create(GameWorld w, Creatures name, int x, int y) {
        switch (name) {
            case wolf:
                return new Wolf(w, x, y);
            case antilope:
                return new Antilope(w, x, y);
            case fox:
                return new Fox(w, x, y);
            case sheep:
                return new Sheep(w, x, y);
            case turtle:
                return new Turtle(w, x, y);
            case dandelion:
                return new Dandelion(w, x, y);
            case grass:
                return new Grass(w, x, y);
            case guarana:
                return new Guarana(w, x, y);
            case nightshade:
                return new NightShade(w, x, y);
        }
        return null;
    }

    public enum Creatures {
        wolf,
        antilope,
        fox,
        sheep,
        turtle,
        dandelion,
        grass,
        guarana,
        nightshade
    }
}
