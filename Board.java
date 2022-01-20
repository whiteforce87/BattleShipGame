package battleShipGame;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Board extends GameObject implements Attackable {
	// Every player has a board and ships as squares on it.

	private Scanner sc = new Scanner(System.in);
	// We collect our and oppenent's ships into ArrayLists
	private ArrayList<Ship> yourShips = new ArrayList<>();
	private ArrayList<Ship> computerShips = new ArrayList<>();

	// We create ocean on board by using squares like coordinates

	private Squares[][] ocean = new Squares[getSize()][getSize()];

	public Board(int size, String name) {
		super(size, name);

	}

	/*
	 * We create ocean on board by squares manually, and then replace the ships into
	 * them. After that same progress happens for the computer as randomly.
	 */
	public void generateOcean() {

		for (int i = 0; i < ocean.length; i++) {
			for (int j = 0; j < ocean.length; j++) {
				ocean[i][j] = new Squares(i, j, SquareStatus.OCEAN);
			}
		}
	}

	// We add ships on our board. Also while adding we create the ships as objects.
	// We separate the ships according to
	// their size and direction. All the ships are collected into ArrayLists.
	// If we player write a wrong letter it goes into try catch block and wants to
	// change it with a correct one.
	public Ship addShip(Ship ship) {

		boolean situation = true;
		while (situation == true) {
			try {

				System.out.println("Where to put the upper/left side of your " + ship.getName());
				System.out.println("Enter enter a letter for X coordinate (A-J):  ");
				String letter = "";
				int number1 = 0;
				int number2 = 0;
				boolean condition = true;

				while (condition == true) {

					try {
						letter = sc.next();
						number2 = (int) (letter.toUpperCase().charAt(0)) - 65;
						if (number2 != 0 && number2 != 1 && number2 != 2 && number2 != 3 && number2 != 4 && number2 != 5
								&& number2 != 6 && number2 != 7 && number2 != 8 && number2 != 9) {

							throw new Exception();
						} else {
							System.out.println("Enter enter a Number for Y coordinate: ");
							number1 = sc.nextInt();
							condition = false;
						}
					} catch (Exception e) {
						System.out.println("Enterence is not valid, please enter a valid letter (A-J):");

					}
				}

				System.out.println("Ship is vertical or horizontal ? (V/H)");
				String answer = "";

				boolean condition2 = true;
				while (condition2 == true) {

					try {
						answer = sc.next();

						if (!answer.toUpperCase().equals("V") && !answer.toUpperCase().equals("H")) {

							throw new Exception();
						} else {
							condition2 = false;
						}
					} catch (Exception e) {
						System.out.println("Enterence is not valid, please enter a valid letter (V/H):");

					}
				}

				if (answer.equalsIgnoreCase("V")) {

					switch (ship.getSize()) {
					case 5: {
						ArrayList<Squares> shipList1 = new ArrayList<>();
						for (int i = 0; i < ship.getSize(); i++) {
							ocean[number1 + i][number2].setSquareStatus(SquareStatus.SHIP);
							shipList1.add(ocean[number1 + i][number2]);
						}
						ship = new Frigate(shipList1, Direction.VERTICAL, 5, "Frigate");
						break;
					}
					case 4: {
						ArrayList<Squares> shipList2 = new ArrayList<>();
						for (int i = 0; i < ship.getSize(); i++) {
							ocean[number1 + i][number2].setSquareStatus(SquareStatus.SHIP);
							shipList2.add(ocean[number1 + i][number2]);
						}
						ship = new Cruiser(shipList2, Direction.VERTICAL, 4, "Cruiser");
						break;
					}

					case 3: {
						ArrayList<Squares> shipList3 = new ArrayList<>();
						for (int i = 0; i < ship.getSize(); i++) {
							ocean[number1 + i][number2].setSquareStatus(SquareStatus.SHIP);
							shipList3.add(ocean[number1 + i][number2]);
						}
						ship = new Submarine(shipList3, Direction.VERTICAL, 3, "Submarine");
						break;
					}
					case 2: {
						ArrayList<Squares> shipList4 = new ArrayList<>();
						for (int i = 0; i < ship.getSize(); i++) {
							ocean[number1 + i][number2].setSquareStatus(SquareStatus.SHIP);
							shipList4.add(ocean[number1 + i][number2]);
						}
						ship = new PatrolBoat(shipList4, Direction.VERTICAL, 2, "PatrolBoat");
						break;
					}
					}

					situation = false;
					yourShips.add(ship);

				} else if (answer.equalsIgnoreCase("H")) {

					switch (ship.getSize()) {
					case 5: {
						ArrayList<Squares> shipList5 = new ArrayList<>();
						for (int i = 0; i < ship.getSize(); i++) {
							ocean[number1][number2 + i].setSquareStatus(SquareStatus.SHIP);
							shipList5.add(ocean[number1][number2 + i]);
						}
						ship = new Frigate(shipList5, Direction.HORIZONTAL, 5, "Frigate");
						break;
					}
					case 4: {
						ArrayList<Squares> shipList6 = new ArrayList<>();
						for (int i = 0; i < ship.getSize(); i++) {
							ocean[number1][number2 + i].setSquareStatus(SquareStatus.SHIP);
							shipList6.add(ocean[number1][number2 + i]);
						}
						ship = new Cruiser(shipList6, Direction.HORIZONTAL, 4, "Cruiser");
						break;
					}

					case 3: {
						ArrayList<Squares> shipList7 = new ArrayList<>();
						for (int i = 0; i < ship.getSize(); i++) {
							ocean[number1][number2 + i].setSquareStatus(SquareStatus.SHIP);
							shipList7.add(ocean[number1][number2 + i]);

						}
						ship = new Submarine(shipList7, Direction.HORIZONTAL, 3, "Submarine");
						break;
					}
					case 2: {
						ArrayList<Squares> shipList8 = new ArrayList<>();
						for (int i = 0; i < ship.getSize(); i++) {
							ocean[number1][number2 + i].setSquareStatus(SquareStatus.SHIP);
							shipList8.add(ocean[number1][number2 + i]);
						}
						ship = new PatrolBoat(shipList8, Direction.HORIZONTAL, 2, "PatrolBoat");
						break;
					}
					}
					situation = false;
					yourShips.add(ship);
				}
			} catch (Exception e) {
				System.out.println("You entered a wrong  Y coordinate. You are out of the board, Try again!!");
			}
		}
		return ship;
	}

	// We generate random numbers for the size and the direction of the ships. And
	// then place and create the ships.
	public Ship addRandomShip(Ship ship) {

		Random rnd = new Random();
		int number1 = rnd.nextInt(11 - ship.getSize());
		Random rnd2 = new Random();
		int number2 = rnd2.nextInt(11 - ship.getSize());

		if (ship.getSize() == 5) {
			while (ocean[number1][number2].getSquareStatus().equals(SquareStatus.SHIP)
					|| ocean[number1 + 1][number2].getSquareStatus().equals(SquareStatus.SHIP)
					|| ocean[number1 + 2][number2].getSquareStatus().equals(SquareStatus.SHIP)
					|| ocean[number1 + 3][number2].getSquareStatus().equals(SquareStatus.SHIP)
					|| ocean[number1 + 4][number2].getSquareStatus().equals(SquareStatus.SHIP)
					|| ocean[number1][number2].getSquareStatus().equals(SquareStatus.SHIP)
					|| ocean[number1][number2 + 1].getSquareStatus().equals(SquareStatus.SHIP)
					|| ocean[number1][number2 + 2].getSquareStatus().equals(SquareStatus.SHIP)
					|| ocean[number1][number2 + 3].getSquareStatus().equals(SquareStatus.SHIP)
					|| ocean[number1][number2 + 4].getSquareStatus().equals(SquareStatus.SHIP)) {

				number1 = rnd.nextInt(11 - ship.getSize());
				number2 = rnd2.nextInt(11 - ship.getSize());

			}
		} else if (ship.getSize() == 4) {
			while (ocean[number1][number2].getSquareStatus().equals(SquareStatus.SHIP)
					|| ocean[number1 + 1][number2].getSquareStatus().equals(SquareStatus.SHIP)
					|| ocean[number1 + 2][number2].getSquareStatus().equals(SquareStatus.SHIP)
					|| ocean[number1 + 3][number2].getSquareStatus().equals(SquareStatus.SHIP)
					|| ocean[number1][number2].getSquareStatus().equals(SquareStatus.SHIP)
					|| ocean[number1][number2 + 1].getSquareStatus().equals(SquareStatus.SHIP)
					|| ocean[number1][number2 + 2].getSquareStatus().equals(SquareStatus.SHIP)
					|| ocean[number1][number2 + 3].getSquareStatus().equals(SquareStatus.SHIP)) {

				number1 = rnd.nextInt(11 - ship.getSize());
				number2 = rnd2.nextInt(11 - ship.getSize());

			}
		} else if (ship.getSize() == 3) {
			while (ocean[number1][number2].getSquareStatus().equals(SquareStatus.SHIP)
					|| ocean[number1 + 1][number2].getSquareStatus().equals(SquareStatus.SHIP)
					|| ocean[number1 + 2][number2].getSquareStatus().equals(SquareStatus.SHIP)

					|| ocean[number1][number2].getSquareStatus().equals(SquareStatus.SHIP)
					|| ocean[number1][number2 + 1].getSquareStatus().equals(SquareStatus.SHIP)
					|| ocean[number1][number2 + 2].getSquareStatus().equals(SquareStatus.SHIP)) {

				number1 = rnd.nextInt(11 - ship.getSize());
				number2 = rnd2.nextInt(11 - ship.getSize());

			}
		} else if (ship.getSize() == 2) {
			while (ocean[number1][number2].getSquareStatus().equals(SquareStatus.SHIP)
					|| ocean[number1 + 1][number2].getSquareStatus().equals(SquareStatus.SHIP)
					|| ocean[number1][number2].getSquareStatus().equals(SquareStatus.SHIP)
					|| ocean[number1][number2 + 1].getSquareStatus().equals(SquareStatus.SHIP)) {

				number1 = rnd.nextInt(11 - ship.getSize());
				number2 = rnd2.nextInt(11 - ship.getSize());

			}
		}

		Random rnd3 = new Random();
		int number3 = rnd3.nextInt(2);

		if (number3 == 0) {

			ArrayList<Squares> shipList = new ArrayList<>();
			for (int i = 0; i < ship.getSize(); i++) {
				ocean[number1 + i][number2].setSquareStatus(SquareStatus.SHIP);
				shipList.add(ocean[number1 + i][number2]);
			}

			switch (ship.getSize()) {
			case 5: {
				ArrayList<Squares> shipList1 = new ArrayList<>();
				for (int i = 0; i < ship.getSize(); i++) {
					ocean[number1 + i][number2].setSquareStatus(SquareStatus.SHIP);
					shipList1.add(ocean[number1 + i][number2]);
				}
				ship = new Frigate(shipList1, Direction.VERTICAL, 5, "Frigate");
				break;
			}
			case 4: {
				ArrayList<Squares> shipList2 = new ArrayList<>();
				for (int i = 0; i < ship.getSize(); i++) {
					ocean[number1 + i][number2].setSquareStatus(SquareStatus.SHIP);
					shipList2.add(ocean[number1 + i][number2]);
				}
				ship = new Cruiser(shipList2, Direction.VERTICAL, 4, "Cruiser");
				break;
			}

			case 3: {
				ArrayList<Squares> shipList3 = new ArrayList<>();
				for (int i = 0; i < ship.getSize(); i++) {
					ocean[number1 + i][number2].setSquareStatus(SquareStatus.SHIP);
					shipList3.add(ocean[number1 + i][number2]);
				}
				ship = new Submarine(shipList3, Direction.VERTICAL, 3, "Submarine");
				break;
			}
			case 2: {
				ArrayList<Squares> shipList4 = new ArrayList<>();
				for (int i = 0; i < ship.getSize(); i++) {
					ocean[number1 + i][number2].setSquareStatus(SquareStatus.SHIP);
					shipList4.add(ocean[number1 + i][number2]);
				}
				ship = new PatrolBoat(shipList4, Direction.VERTICAL, 2, "PatrolBoat");
				break;
			}
			}

			computerShips.add(ship);

		}
		if (number3 == 1) {
			ArrayList<Squares> shipList = new ArrayList<>();
			for (int i = 0; i < ship.getSize(); i++) {
				ocean[number1][number2 + i].setSquareStatus(SquareStatus.SHIP);
				shipList.add(ocean[number1][number2 + i]);
			}
			switch (ship.getSize()) {
			case 5: {
				ArrayList<Squares> shipList5 = new ArrayList<>();
				for (int i = 0; i < ship.getSize(); i++) {
					ocean[number1][number2 + i].setSquareStatus(SquareStatus.SHIP);
					shipList5.add(ocean[number1][number2 + i]);
				}
				ship = new Frigate(shipList5, Direction.HORIZONTAL, 5, "Frigate");
				break;
			}
			case 4: {
				ArrayList<Squares> shipList6 = new ArrayList<>();
				for (int i = 0; i < ship.getSize(); i++) {
					ocean[number1][number2 + i].setSquareStatus(SquareStatus.SHIP);
					shipList6.add(ocean[number1][number2 + i]);
				}
				ship = new Cruiser(shipList6, Direction.HORIZONTAL, 4, "Cruiser");
				break;
			}

			case 3: {
				ArrayList<Squares> shipList7 = new ArrayList<>();
				for (int i = 0; i < ship.getSize(); i++) {
					ocean[number1][number2 + i].setSquareStatus(SquareStatus.SHIP);
					shipList7.add(ocean[number1][number2 + i]);

				}
				ship = new Submarine(shipList7, Direction.HORIZONTAL, 3, "Submarine");
				break;
			}
			case 2: {
				ArrayList<Squares> shipList8 = new ArrayList<>();
				for (int i = 0; i < ship.getSize(); i++) {
					ocean[number1][number2 + i].setSquareStatus(SquareStatus.SHIP);
					shipList8.add(ocean[number1][number2 + i]);
				}
				ship = new PatrolBoat(shipList8, Direction.HORIZONTAL, 2, "PatrolBoat");
				break;
			}
			}

			computerShips.add(ship);
		}
		return ship;
	}
	// Checking all the ArrayList with for loop and if a ship gets shots for all the
	// square of itself we change the
	// square status with sunken and change its size,direction and name.

	public void isSunk(ArrayList<Ship> ships) {

		for (Ship ship : ships) {
			int count = 0;
			for (int i = 0; i < ship.getSize(); i++) {
				if (ship.getShipList().get(i).getSquareStatus().equals(SquareStatus.SHOT)) {
					count++;
				}
			}
			if (count == ship.getSize()) {
				System.out.println("\n****Yes you got the " + ship.getName() + " SUNKEN!****\n");
				for (int i = 0; i < ship.getSize(); i++) {
					ship.getShipList().get(i).setSquareStatus(SquareStatus.SUNKEN);
				}
				ship.setSize(0);
				ship.setDirection(null);

			}
		}
	}

	// Displaying each square status and board shape of us at the beginning and
	// after each attack
	public String displayBoard() {

		String retval = "";
		System.out.println("------YOUR BOARD-----\n");
		System.out.println(" " + " A " + "B " + "C " + "D " + "E " + "F " + "G " + "H " + "I " + "J ");
		for (int i = 0; i < ocean.length; i++) {
			retval += i;
			for (int j = 0; j < ocean.length; j++) {
				retval += " " + ocean[i][j].getSquareStatus().getSign();
			}
			retval += "\n";
		}
		return retval;
	}

	// Displaying each square status and board shape of computer at the beginning
	// and after each attack
	// This method shows all the ships of the opponent. So it is a cheat and we
	// don't use it.
	public String displayComputerBoard() {

		String retval = "";
		System.out.println("----COMPUTER BOARD----\n");
		System.out.println(" " + " A " + "B " + "C " + "D " + "E " + "F " + "G " + "H " + "I " + "J ");
		for (int i = 0; i < ocean.length; i++) {
			retval += i;
			for (int j = 0; j < ocean.length; j++) {
				retval += " " + ocean[i][j].getSquareStatus().getSign();
			}
			retval += "\n";
		}
		return retval;
	}

	// Displaying each square status and board shape of computer at the beginning
	// and after each attack
	// We use it because it hides the ships of the opponent. We don't change the
	// real status of the ships
	// Just we show the characters on the board.
	public String displayComputerBoardHidden() {

		String retval = "";
		System.out.println("----COMPUTER BOARD----\n");
		System.out.println(" " + " A " + "B " + "C " + "D " + "E " + "F " + "G " + "H " + "I " + "J ");
		for (int i = 0; i < ocean.length; i++) {
			retval += i;

			for (int j = 0; j < ocean.length; j++) {
				if (ocean[i][j].getSquareStatus().equals(SquareStatus.MISS)) {
					retval += " " + "0";
				} else if (ocean[i][j].getSquareStatus().equals(SquareStatus.SHOT)) {
					retval += " " + "X";
				} else if (ocean[i][j].getSquareStatus().equals(SquareStatus.SUNKEN)) {
					retval += " " + "S";
				} else {
					retval += " " + "-";
				}

			}
			retval += "\n";
		}
		return retval;
	}

	// Getting the info of board and ship informations

	public int updateLife() {
		int unShotPart = 0;
		for (Squares[] points : ocean) {
			for (int i = 0; i < ocean.length; i++) {
				if (points[i].getSquareStatus().equals(SquareStatus.SHIP)) {
					unShotPart++;
				}
			}
		}
		System.out.println("Life remaining: " + unShotPart);
		return unShotPart;
	}

	public void getInfo() {

		System.out.println("Board Size:" + getSize());
		for (Ship ship : yourShips) {
			System.out.println("---Your Ships:---");
			System.out.println("Name: " + ship.getName());
			System.out.println("Size: " + ship.getSize());
			System.out.println("Direction: " + ship.getDirection());
		}

		for (Ship ship : computerShips) {
			System.out.println("---Computer Ships:---");
			System.out.println("Name: " + ship.getName());
			System.out.println("Size: " + ship.getSize());
			System.out.println("Direction: " + ship.getDirection());
		}

	}

	public ArrayList<Ship> getYourShips() {
		return yourShips;
	}

	public void setYourShips(ArrayList<Ship> yourShips) {
		this.yourShips = yourShips;
	}

	public ArrayList<Ship> getComputerShips() {
		return computerShips;
	}

	public void setComputerShips(ArrayList<Ship> computerShips) {
		this.computerShips = computerShips;
	}

	public Squares[][] getOcean() {
		return ocean;
	}

	public void setOcean(Squares[][] ocean) {
		this.ocean = ocean;
	}

	@Override
	public void manuelAttack() {
		System.out.println("You are attacking!");

	}

	@Override
	public void randomAttack() {
		System.out.println("Computer ia attacking");

	}

}
