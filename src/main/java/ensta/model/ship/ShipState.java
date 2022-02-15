package ensta.model.ship;

public class ShipState {
    private AbstractShip ship;
    private boolean struck;

    public ShipState() {
        this(null);
    }

    public ShipState(AbstractShip ship) {
        this.ship = ship;
        this.struck = false;
    }

    public boolean isStruck() {
        return struck;
    }

    public void setStruck(boolean struck) {
        this.struck = struck;
    }

    @Override
    public String toString() {
        return String.valueOf(ship.getLabel());
    }

    public AbstractShip getShip() {
        return ship;
    }
}
