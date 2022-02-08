package ensta.model.ship;

import ensta.util.Orientation;

public class Submarine extends AbstractShip {
    Submarine(Orientation orientation) {
        super('D', "Submarine", 3, orientation);
    }

    Submarine() {
        super('D', "Submarine", 3, Orientation.EAST);
    }
    
}
