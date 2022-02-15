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
        // new Game().init().run();
        List<AbstractShip> ships = new ArrayList<AbstractShip>();
        ships.add(new Destroyer());
        ships.add(new BattleShip());
        ships.add(new Carrier());
        ships.add(new Submarine());
        ships.add(new Submarine());

        Board board = new Board();
        Board opponentBoard = new Board();
        Player player = new Player(board, opponentBoard, ships);
        opponentBoard.putShip(new Destroyer(), new Coords(0, 0));
        // Hit test_hit = board.sendHit(new Coords(0, 0));
        // test_hit = board.sendHit(new Coords(1, 0));
        // board.print();
        // System.out.println(test_hit);
        // player.putShips();
        Coords coords = new Coords();
        player.sendHit(coords);
        player.sendHit(coords);
        board.print();
        opponentBoard.print();
    }

}
