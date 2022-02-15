package ensta.model;

import java.util.Arrays;

import ensta.model.ship.AbstractShip;
import ensta.model.ship.ShipState;
import ensta.util.ColorUtil;
import ensta.util.Orientation;

public class Board implements IBoard {

	private static final int DEFAULT_SIZE = 10;

	private String name;
	private ShipState[][] boats;
	private Boolean[][] hits;
	private int size;

	public String getName() {
		return name;
	}

	public ShipState[][] getBoats() {
		return boats;
	}

	public Boolean[][] getHits() {
		return hits;
	}

	public Board() {
		this("default_name", DEFAULT_SIZE);
	}

	public Board(String name, int size) {
		this.name = name;
		this.size = size;
		boats = new ShipState[size][size];
		for (ShipState[] row : boats)
			Arrays.fill(row, new ShipState());
		hits = new Boolean[size][size];
	}

	public Board(String name) {
		this(name, 10);
	}

	public void print() {
		System.out.println("\nFrappes");
		String first_row = " ";
		for (int j = 0; j < hits[0].length; j++) {
			first_row += " " + Character.toString(j + 65);
		}
		System.out.println(first_row);
		for (int i = 0; i < hits.length; i++) {
			String row_to_print = Integer.toString(i + 1);
			for (int j = 0; j < hits[0].length; j++) {
				if (hits[i][j] == null)
					row_to_print += " " + ".";
				else
					row_to_print += " " + (hits[i][j] ? ColorUtil.colorize("X", ColorUtil.Color.RED)
							: ColorUtil.colorize("X", ColorUtil.Color.WHITE));
			}
			System.out.println(row_to_print);
		}
		System.out.println("Navires");
		first_row = " ";
		for (int j = 0; j < boats[0].length; j++) {
			first_row += " " + Character.toString(j + 65);
		}
		System.out.println(first_row);
		for (int i = 0; i < boats.length; i++) {
			String row_to_print = Integer.toString(i + 1);
			for (int j = 0; j < boats[0].length; j++) {
				if (boats[i][j].getShip() == null)
					row_to_print += " "
							+ (boats[i][j].isStruck() ? ColorUtil.colorize("X", ColorUtil.Color.WHITE) : ".");
				else {
					if (boats[i][j].isStruck())
						row_to_print += " " + ColorUtil.colorize("X", ColorUtil.Color.RED);
					else
						row_to_print += " " + boats[i][j].getShip().getLabel();
				}
			}
			System.out.println(row_to_print);
		}
	}

	public boolean canPutShip(AbstractShip ship, Coords coords) {
		Orientation o = ship.getOrientation();
		int dx = 0, dy = 0;
		if (coords.getX() >= this.size || coords.getY() >= this.size || coords.getX() < 0
				|| coords.getY() < 0)
			return false;
		if (o == Orientation.EAST) {
			if (coords.getX() + ship.getLength() > this.size) {
				return false;
			}
			dx = 1;
		} else if (o == Orientation.SOUTH) {
			if (coords.getY() + ship.getLength() > this.size) {
				return false;
			}
			dy = 1;
		} else if (o == Orientation.NORTH) {
			if (coords.getY() - ship.getLength() < 0) {
				return false;
			}
			dy = -1;
		} else if (o == Orientation.WEST) {
			if (coords.getX() - ship.getLength() < 0) {
				return false;
			}
			dx = -1;
		}

		Coords iCoords = new Coords(coords);

		for (int i = 0; i < ship.getLength(); ++i) {
			if (this.hasShip(iCoords)) {
				return false;
			}
			iCoords.setX(iCoords.getX() + dx);
			iCoords.setY(iCoords.getY() + dy);
		}

		return true;
	}

	@Override
	public int getSize() {
		return this.size;
	}

	@Override
	public boolean putShip(AbstractShip ship, Coords coords) {
		if (canPutShip(ship, coords)) {
			if (ship.getOrientation() == Orientation.EAST || ship.getOrientation() == Orientation.WEST)
				for (int j = 0; j < ship.getLength()
						&& j > -ship.getLength(); j = j + ship.getOrientation().getIncrement())
					boats[coords.getY()][coords.getX() + j] = new ShipState(ship);
			else
				for (int i = 0; i < ship.getLength()
						&& i > -ship.getLength(); i = i + ship.getOrientation().getIncrement())
					boats[coords.getY() + i][coords.getX()] = new ShipState(ship);
			return true;
		} else
			return false;
	}

	@Override
	public boolean hasShip(Coords coords) {
		return !(boats[coords.getY()][coords.getX()].getShip() == null);
	}

	@Override
	public void setHit(boolean hit, Coords coords) {
		hits[coords.getY()][coords.getX()] = hit;
	}

	@Override
	public Boolean getHit(Coords coords) {
		return hits[coords.getY()][coords.getX()];
	}

	@Override
	public Hit sendHit(Coords res) {
		if (res.getX() < 0 || res.getX() >= this.size || res.getY() < 0 || res.getY() >= this.size) {
			System.out.println("Les coordonnées doivent être valides !");
			return null;
		}
		if (hasShip(res)) {
			ShipState current_ship_state = boats[res.getY()][res.getX()];
			if (!current_ship_state.isStruck()) {
				current_ship_state.getShip().addStrike();
				current_ship_state.setStruck(true);
			} else {
				System.out.println("Il faut viser une case qui n'a pas déjà été attaquée !");
				return null;
			}
			if (current_ship_state.getShip().isSunk())
				return Hit.valueOf(current_ship_state.getShip().getName().toUpperCase());
			else
				return Hit.STRIKE;
		} else {
			return Hit.MISS;
		}
	}
}
