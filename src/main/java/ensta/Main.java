package ensta;

import ensta.controller.Game;
import ensta.model.Board;
import ensta.model.Coords;
import ensta.model.Player;

import java.util.ArrayList;
import java.util.List;
import ensta.model.ship.AbstractShip;
import ensta.model.ship.BattleShip;
import ensta.model.ship.Carrier;
import ensta.model.ship.Destroyer;
import ensta.model.ship.Submarine;

public class Main {
    public static void main(String args[]) {
        // new Game().init().run();
        List<AbstractShip> ships = new ArrayList<AbstractShip>();
        ships.add(new Destroyer());
        ships.add(new BattleShip());
        ships.add(new Carrier());
        ships.add(new Submarine());

        Player player = new Player(new Board(), new Board(), ships);
        player.putShips();
    }

}
