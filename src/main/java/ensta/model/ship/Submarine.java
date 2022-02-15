package ensta.model.ship;

import ensta.util.Orientation;

public class Submarine extends AbstractShip {
    public Submarine(Orientation orientation) {
        super('S', "Submarine", 3, orientation);
    }

    public Submarine() {
        super('S', "Submarine", 3, Orientation.EAST);
    }
    
}
