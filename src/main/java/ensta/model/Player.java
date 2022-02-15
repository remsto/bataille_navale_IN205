package ensta.model;

import java.io.Serializable;
import java.util.List;

import ensta.model.ship.AbstractShip;
import ensta.util.Orientation;
import ensta.view.InputHelper;

public class Player {
	/*
	 * ** Attributs
	 */
	private Board board;
	protected Board opponentBoard;
	private int destroyedCount;
	protected AbstractShip[] ships;
	private boolean lose;

	/*
	 * ** Constructeur
	 */
	public Player(Board board, Board opponentBoard, List<AbstractShip> ships) {
		this.setBoard(board);
		this.ships = ships.toArray(new AbstractShip[0]);
		this.opponentBoard = opponentBoard;
	}

	/*
	 * ** Méthodes
	 */

	/**
	 * Read keyboard input to get ships coordinates. Place ships on given
	 * coodrinates.
	 */
	public void putShips() {
		boolean done = false;
		int i = 0;
		do {
			AbstractShip ship = ships[i];
			String msg = String.format("placer %d : %s(%d)", i + 1, ship.getName(), ship.getLength());
			System.out.println(msg);
			InputHelper.ShipInput res = InputHelper.readShipInput();
			if (res.orientation.equals("north"))
				ship.setOrientation(Orientation.NORTH);
			else if (res.orientation.equals("south"))
				ship.setOrientation(Orientation.SOUTH);
			else if (res.orientation.equals("west"))
				ship.setOrientation(Orientation.WEST);
			else if (res.orientation.equals("east"))
				ship.setOrientation(Orientation.EAST);
			boolean could_put_ship = board.putShip(ship, new Coords(res.x, res.y));
			if (!could_put_ship)
				System.err.println("Mauvaises coordonnées ! Hors de la carte ou collision avec un autre bateau !");
			else {
				board.print();
				++i;
			}
			done = i == 5;
		} while (!done);

	}

	public Hit sendHit(Coords coords) {
		boolean done = false;
		Hit hit = null;
		do {
			System.out.println("où frapper?");
			InputHelper.CoordInput hitInput = InputHelper.readCoordInput();
			Hit hitOutput = this.opponentBoard.sendHit(new Coords(hitInput.x, hitInput.y));
			if (hitOutput != null) {
				System.out.println(hitOutput);
				coords.setCoords(new Coords(hitInput.x, hitInput.y));
				board.setHit(true, coords);
				return hitOutput;
			} else {
				System.out.println("Problème avec le tir, veuillez le refaire");
			}
		} while (!done);

		return hit;
	}

	public AbstractShip[] getShips() {
		return ships;
	}

	public void setShips(AbstractShip[] ships) {
		this.ships = ships;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public int getDestroyedCount() {
		return destroyedCount;
	}

	public void setDestroyedCount(int destroyedCount) {
		this.destroyedCount = destroyedCount;
	}

	public boolean isLose() {
		return lose;
	}

	public void setLose(boolean lose) {
		this.lose = lose;
	}
}
