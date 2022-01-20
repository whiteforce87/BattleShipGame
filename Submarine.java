package battleShipGame;

import java.util.ArrayList;

//Every ship type is a ship so there is a inheritance and we use extends for it.
public class Submarine extends Ship {
	// Every ship type has squares and square status. Size is constant.

	public Submarine(ArrayList<Squares> shipList, Direction direction, int size, String name) {
		super(shipList, direction, size, name);
	}

	public Submarine(int size, String name) {
		super(size, name);
	}

	// Information methods are overriden and give each ships information separately.

	@Override
	public void getInfo() {
		System.out.println("Name: " + getName());
		System.out.println("Size: " + getSize());
		System.out.println("Direction: " + getDirection());

	}

}
