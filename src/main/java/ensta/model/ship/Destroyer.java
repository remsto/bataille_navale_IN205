package ensta.model.ship;

import ensta.util.Orientation;

public class Destroyer extends AbstractShip {
    Destroyer(Orientation orientation) {
        super('D', "Destroyer", 2, orientation);
    }

    Destroyer() {
        super('D', "Destroyer", 2, Orientation.EAST);
    }
}
