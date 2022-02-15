package ensta.controller;

import java.util.ArrayList;
import java.util.List;

import ensta.ai.BattleShipsAI;
import ensta.model.Board;
import ensta.model.Coords;
import ensta.model.Hit;
import ensta.model.ship.*;

public class TestGame {

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Board board = new Board();
        board.print();
        AbstractShip[] ships = {
                new Destroyer(),
                new BattleShip(),
                new Carrier(), new Submarine(),
                new Submarine() };
        BattleShipsAI ai = new BattleShipsAI(board, board);
        ai.putShips(ships);
        int nbDetruit = 0;
        Coords coords = new Coords();
        while (nbDetruit != 10) {
            Hit hitOutput = ai.sendHit(coords);
            System.out.println("Tir en " + coords.toString() + " : " + hitOutput.toString());
            
            board.print();
            sleep(1000);
        }
    }
}
