package ensta.model.ship;

import ensta.util.Orientation;

public class AbstractShip {

    private Character label;
    private String name;
    private int length;
    private Orientation orientation;
    private int strikeCount;

    public AbstractShip(Character label, String name, int length, Orientation orientation) {
        this.label = label;
        this.name = name;
        this.length = length;
        this.orientation = orientation;
        this.strikeCount = 0;
    }

    public void addStrike() {
        strikeCount++;
    };

    public Character getLabel() {
        return label;
    }

    public boolean isSunk() {
        return (strikeCount >= length);
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
