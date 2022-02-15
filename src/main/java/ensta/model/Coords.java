package ensta.model;

public class Coords {
    private int x, y;

    public Coords(Coords coords) {
        this(coords.getX(), coords.getY());
    }

    public Coords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coords() {
        this(0, 0);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setCoords(Coords coords) {
        this.x = coords.x;
        this.y = coords.y;
    }

    public boolean isInBoard(int size) {
        return false;
    }

    public static Coords randomCoords(int size) {
        return null;
    }
}
