package ensta.model.ship;

import ensta.util.Orientation;

public class BattleShip extends AbstractShip {
    BattleShip(Orientation orientation) {
        super('D', "BattleShip", 4, orientation);
    }

    BattleShip() {
        super('D', "BattleShip", 4, Orientation.EAST);
    }
}
