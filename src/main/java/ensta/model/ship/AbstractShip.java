package ensta.model.ship;

import ensta.util.Orientation;

public class AbstractShip {

    private Character label;
    private String name;
    private int length;
    private Orientation orientation;

    public AbstractShip(Character label, String name, int length, Orientation orientation) {
        this.label = label;
        this.name = name;
        this.length = length;
        this.orientation = orientation;
    }

    public Character getLabel() {
        return label;
    }

    public boolean isSunk() {
        return false;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

}
