package ensta.model.ship;

import ensta.util.Orientation;

public class Carrier extends AbstractShip {
    Carrier(Orientation orientation) {
        super('D', "Carrier", 5, orientation);
    }

    Carrier() {
        super('D', "Carrier", 5, Orientation.EAST);
    }
}
