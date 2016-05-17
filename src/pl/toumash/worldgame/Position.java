package pl.toumash.worldgame;

public class Position implements Cloneable {
    private int x;

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position move(Direction d) {
        // deltas
        int dX = 0;
        int dY = 0;
        switch (d) {
            case up:
                dY -= 1;
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
        x += dX;
        y += dY;
        return this;
    }
}
