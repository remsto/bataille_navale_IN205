package ensta.model.ship;

public class ShipState {
    private AbstractShip ship;
    public Boolean struck;

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

    public void setStruck(boolean strucked) {
        this.struck = strucked;
    }

    @Override
    public String toString() {
        return String.valueOf(ship.getLabel());
    }

    public AbstractShip getShip() {
        return ship;
    }
}
