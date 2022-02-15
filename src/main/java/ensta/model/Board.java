package ensta.model;

import ensta.model.ship.AbstractShip;
import ensta.util.Orientation;

public class Board implements IBoard {

	private static final int DEFAULT_SIZE = 10;

	private String name;
	private Character[][] boats;
	private boolean[][] hits;
	private int size;

	public String getName() {
		return name;
	}

	public Character[][] getBoats() {
		return boats;
	}

	public boolean[][] getHits() {
		return hits;
	}

	public Board() {
		this("default_name", DEFAULT_SIZE);
	}

	public Board(String name, int size) {
		this.name = name;
		this.size = size;
		boats = new Character[size][size];
		hits = new boolean[size][size];
	}

	public Board(String name) {
		this(name, 10);
	}

	public void print() {
		System.out.println("Navires");
		String first_row = " ";
		for (int j = 0; j < boats[0].length; j++) {
			first_row += " " + Character.toString(j + 65);
		}
		System.out.println(first_row);
		for (int i = 0; i < boats.length; i++) {
			String row_to_print = Integer.toString(i + 1);
			for (int j = 0; j < boats[0].length; j++) {
				row_to_print += " " + (boats[i][j] == null ? "." : boats[i][j]);
			}
			System.out.println(row_to_print);
		}
		System.out.println("\nFrappes");
		first_row = " ";
		for (int j = 0; j < hits[0].length; j++) {
			first_row += " " + Character.toString(j + 65);
		}
		System.out.println(first_row);
		for (int i = 0; i < hits.length; i++) {
			String row_to_print = Integer.toString(i + 1);
			for (int j = 0; j < hits[0].length; j++) {
				row_to_print += " " + (hits[i][j] ? "x" : ".");
			}
			System.out.println(row_to_print);
		}
	}

	public boolean canPutShip(AbstractShip ship, Coords coords) {
		Orientation o = ship.getOrientation();
		int dx = 0, dy = 0;
		if (coords.getX() + 1 > this.size || coords.getY() + 1 > this.size || coords.getX() + 1 <= 0
				|| coords.getY() + 1 <= 0)
			return false;
		if (o == Orientation.EAST) {
			if (coords.getX() + ship.getLength() >= this.size) {
				return false;
			}
			dx = 1;
		} else if (o == Orientation.SOUTH) {
			if (coords.getY() + ship.getLength() >= this.size) {
				return false;
			}
			dy = 1;
		} else if (o == Orientation.NORTH) {
			if (coords.getY() + 1 - ship.getLength() < 0) {
				return false;
			}
			dy = -1;
		} else if (o == Orientation.WEST) {
			if (coords.getX() + 1 - ship.getLength() < 0) {
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
				for (int j = 0; j < ship.getLength(); j = j + ship.getOrientation().getIncrement())
					boats[coords.getY() - 1][coords.getX() + j - 1] = ship.getLabel();
			else
				for (int i = 0; i < ship.getLength(); i = i + ship.getOrientation().getIncrement())
					boats[coords.getY() + i - 1][coords.getX() - 1] = ship.getLabel();
			return true;
		} else
			return false;
	}

	@Override
	public boolean hasShip(Coords coords) {
		return !(boats[coords.getY()][coords.getX()] == null);
	}

	@Override
	public void setHit(boolean hit, Coords coords) {
		hits[coords.getY()][coords.getX()] = hit;
		boats[coords.getY()][coords.getX()] = null;
	}

	@Override
	public Boolean getHit(Coords coords) {
		return hits[coords.getX()][coords.getY()];
	}

	@Override
	public Hit sendHit(Coords res) {
		// TODO Auto-generated method stub
		return null;
	}
}
