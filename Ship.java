package battleShipGame;

import java.util.ArrayList;

public abstract class Ship extends GameObject {
	// Each ship has name, size, squares, start point, direction and a list they
	// collect in
	private Direction direction;
	private ArrayList<Squares> shipList = new ArrayList<Squares>();

	public Ship(ArrayList<Squares> shipList, Direction direction, int size, String name) {
		super(size, name);
		this.direction = direction;
		this.shipList = shipList;
	}

	public Ship(int size, String name) {
		super(size, name);
	}

	// Information of total ship of and square status information
	public abstract void getInfo();

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public ArrayList<Squares> getShipList() {
		return shipList;
	}

	public void setShipList(ArrayList<Squares> shipList) {
		this.shipList = shipList;
	}

}
