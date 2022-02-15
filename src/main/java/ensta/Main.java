package ensta;

import ensta.controller.Game;
import ensta.model.Board;
import ensta.model.Coords;
import ensta.model.ship.Carrier;
import ensta.model.ship.Destroyer;

public class Main {
    public static void main(String args[]) {
        // new Game().init().run();
        Board board = new Board();
        boolean test1 = board.putShip(new Destroyer(), new Coords(1, 1));
        System.out.println(test1);
        board.print();
    }

}
