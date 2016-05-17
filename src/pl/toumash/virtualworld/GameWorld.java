package pl.toumash.virtualworld;

import pl.toumash.virtualworld.creature.Animal;
import pl.toumash.virtualworld.creature.Creature;
import pl.toumash.virtualworld.creature.CreaturesFactory;
import pl.toumash.virtualworld.creature.animal.Antilope;
import pl.toumash.virtualworld.creature.animal.Human;
import pl.toumash.virtualworld.creature.animal.Turtle;
import pl.toumash.virtualworld.creature.plant.Guarana;
import pl.toumash.virtualworld.creature.plant.NightShade;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class GameWorld implements Serializable {
    private ArrayList<Creature> creatures = new ArrayList<>();
    private ArrayList<Creature> corpses = new ArrayList<>();
    private Human player;
    private int width, height;
    private ArrayList<String> events = new ArrayList<>(20);

    GameWorld(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public static Creature whoAttacks(Creature a, int i, Creature b, int j) {
        if (a.getPriority() > b.getPriority()) {
            return a;
        } else if (a.getPriority() < b.getPriority()) {
            return b;
        }

        if (a.getStrength() > b.getStrength()) {
            return a;
        } else if (a.getStrength() < b.getStrength()) {
            return b;
        }

        if (i > j) {
            return a;
        } else {
            return b;
        }
    }

    public void randominit() {
        Random r = new Random();
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                if (x != getWidth() / 2 && y != getHeight() / 2)
                    creatures.add(CreaturesFactory.create(this, CreaturesFactory.Creatures.values()[r.nextInt(CreaturesFactory.Creatures.values().length)], x, y));
            }
        }
        Human h = new Human(this, getWidth() / 2, getHeight() / 2);
        creatures.add(h);
        this.player = h;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void spawn(Creature creature) {
        this.creatures.add(creature);
    }

    public void draw(Graphics g, double scaleX, double scaleY) {
        for (Creature c : creatures) {
            c.draw(g, scaleX, scaleY);
        }
    }

    public ArrayList<String> getEvents() {
        return events;
    }

    public boolean isOccupied(int x, int y) {
        for (Creature c : creatures) {
            if (c.getX() == x && c.getY() == y) {
                return true;
            }
        }
        return false;
    }

    public boolean inBounds(int x, int y) {
        return (x >= 0 && x < getWidth()) && (y >= 0 && y < getHeight());
    }

    public boolean isOccupied(int x, int y, Direction d) {
        int dX = 0, dY = 0;
        // TODO: remove duplicate code
        switch (d) {
            case up:
                dY = -1;
                break;
            case down:
                dY = +1;
                break;
            case left:
                dX = -1;
                break;
            case right:
                dX = +1;
                break;
        }
        for (Creature c : creatures) {
            if (c.getX() + dX == x && c.getY() + dY == y) {
                return true;
            }
        }
        return false;
    }

    public void update() {
        clearEvents();
        update(7);
        update(5);
        update(4);
        update(1);

        checkCollisions();
        clearGarbage();
    }

    private void clearEvents() {
        events.clear();
    }

    private void clearGarbage() {
        for (Creature c : corpses) {
            creatures.remove(c);
        }
    }

    public void update(int priority) {
        for (Creature c : creatures) {
            if (priority == c.getPriority() && c.isAlive()) {
                c.update();
            }
        }
    }

    public void addEvent(String x) {
        System.out.println(x);
        events.add(x);
    }

    private void checkCollisions() {
        for (int i = 0, length = creatures.size(); i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (j != i) {
                    Creature a = creatures.get(i);
                    Creature b = creatures.get(j);
                    if (a.getX() == b.getX() && a.getY() == b.getY()) {
                        Creature attacker = whoAttacks(a, i, b, j);
                        collision(attacker, attacker == a ? b : a);
                    }
                }
            }
        }
    }

    private void collision(Creature a, Creature b) {
        if (!a.isAlive() || !b.isAlive()) {
            return;
        }

        if (a instanceof Turtle || b instanceof Turtle) {
            Creature attacker = b;
            if (b instanceof Turtle) {
                attacker = a;
            }
            if (attacker.getStrength() < 5) {
                attacker.cancelMove();
                return;
            }
        }
        if (a instanceof Antilope || b instanceof Antilope) {
            Antilope antilope = (Antilope) (a instanceof Antilope ? a : b);
            if (new Random().nextInt(2) == 0) {
                antilope.flee();
                addEvent("antilope escaped the battle");
                return;
            }
        }
        if (a instanceof Guarana || b instanceof Guarana) {
            Creature animal = (a instanceof Guarana) ? b : a;
            if (animal instanceof Animal) {
                animal.improveStrength(+3);
                return;
            }
        }
        if (a instanceof NightShade || b instanceof NightShade) {
            Creature animal = (a instanceof NightShade) ? b : a;
            if (animal instanceof Animal) {
                Creature nightshade = animal == a ? b : a;
                if (animal.getStrength() < nightshade.getStrength()) {
                    kill(nightshade, animal);
                    return;
                }
            }
        }
        a.collide(b);
    }

    public void kill(Creature killer, Creature victim) {
        victim.kill();
        corpses.add(victim);
        addEvent(killer.getClass().getSimpleName() + " (" + killer.getX() + "," + killer.getY() + ") kills" + victim.getClass().getSimpleName() + " (" + victim.getX() + "," + victim.getY() + ")");
        // TODO: Message to screen
    }

    public void playerMove(Direction dir) {
        if (player != null && player.isAlive()) {
            if (player.canMove(dir))
                player.setNextMove(dir);
        }
    }

    public Creature getCreature(int x, int y) {
        for (Creature c : creatures) {
            if (c.getX() == x && c.getY() == y) {
                return c;
            }
        }
        return null;
    }
}
