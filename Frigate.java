package battleShipGame;

import java.util.ArrayList;

// Every ship type is a ship so there is a inheritance and we use extends for it.
public class Frigate extends Ship {
	// Every ship type has squares and square status. Size is constant.

	public Frigate(ArrayList<Squares> shipList, Direction direction, int size, String name) {
		super(shipList, direction, size, name);
	}

	public Frigate(int size, String name) {
		super(size, name);
	}

	@Override
	public void getInfo() {
		System.out.println("Name: " + getName());
		System.out.println("Size: " + getSize());
		System.out.println("Direction: " + getDirection());

	}

}
