package ensta;

import ensta.controller.Game;
import ensta.model.Board;
import ensta.model.Coords;
import ensta.model.Hit;
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
        new Game().init().run();
    }
}
