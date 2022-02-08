package ensta;

import ensta.controller.Game;
import ensta.model.Board;

public class Main {

    public static void main(String args[]) {
        // new Game().init().run();
        Board board = new Board("oueoue", 20);
        board.print();
    }

}
