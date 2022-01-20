package battleShipGame;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

//Each player has name, a board ,life as 14 for the size of 4 ships and column characters.
public class Player {

	private String name;
	private static int playerLife = 14;
	private static int computerLife = 14;
	private ArrayList<int[][]> turn = new ArrayList<>();
	private Scanner sc = new Scanner(System.in);
	private ArrayList<GameObject> gameObjects = new ArrayList<>();

	public Player() {
		super();

	}

	public Player(String name) {
		this.name = name;

	}

	public Player(ArrayList<GameObject> gameObjects) {
		super();
		this.gameObjects = gameObjects;
	}

	public static void main(String[] args) {

		// Every player has board and ships not only his/her own, but also for
		// opponents. After game start the computer get its own board
		// and ships and our new player gets his/her own board and ships.
		Board board1 = new Board(10, "Player Board");
		Board board2 = new Board(10, "Computer Board");
		Ship playerFrigate = new Frigate(5, "Turkish Frigate");
		Ship PlayerCruiser = new Cruiser(4, "Turkish Cruiser");
		Ship playerSubmarine = new Submarine(3, "Turkish Submarine");
		Ship playerPatrolBoat = new PatrolBoat(2, "Turkish PatrolBoat");
		Ship computerFrigate = new Frigate(5, "U.S. Frigate");
		Ship computerCruiser = new Cruiser(4, "U.S Cruiser");
		Ship computerSubmarine = new Submarine(3, "U.S. Submarine");
		Ship computerPatrolBoat = new PatrolBoat(2, "U.S. PatrolBoat");

		Player player = new Player();

		// We add each object into gameObject ArrayList.
		player.addObject(board1);
		player.addObject(board2);
		player.addObject(playerFrigate);
		player.addObject(PlayerCruiser);
		player.addObject(playerSubmarine);
		player.addObject(playerPatrolBoat);
		player.addObject(computerFrigate);
		player.addObject(computerCruiser);
		player.addObject(computerSubmarine);
		player.addObject(computerPatrolBoat);

		player.gameStart();

	}

	public void gameStart() {

		// When a person start a game it has a player and a opponent as computer. So we
		// create 2 player object.
		Player player1 = new Player("Turkish Navy");
		Player computer = new Player("computer");
		System.out.println("--------Welcome to BattleShipGame--------");

		// Player p1 = new Player(name);

		// player.setBoard1(board1);
		// computer.setBoard2(board2);

		System.out.println("Welcome " + player1.getName() + " This is the board you use\n");
		((Board) gameObjects.get(0)).generateOcean();
		((Board) gameObjects.get(1)).generateOcean();
		System.out.println(((Board) gameObjects.get(0)).displayBoard());
		enterance();
		System.out.println("First put the Frigate\n");
		((Board) gameObjects.get(0)).addShip((Ship) gameObjects.get(2));
		System.out.println("Second put the Cruiser\n");
		((Board) gameObjects.get(0)).addShip((Ship) gameObjects.get(3));
		System.out.println("Third put the Submarine\n");
		((Board) gameObjects.get(0)).addShip((Ship) gameObjects.get(4));
		System.out.println("Finally put the PatrolBoat\n");
		((Board) gameObjects.get(0)).addShip((Ship) gameObjects.get(5));
		System.out.println(((Board) gameObjects.get(0)));

		((Board) gameObjects.get(1)).addRandomShip((Ship) gameObjects.get(6));
		((Board) gameObjects.get(1)).addRandomShip((Ship) gameObjects.get(7));
		((Board) gameObjects.get(1)).addRandomShip((Ship) gameObjects.get(8));
		((Board) gameObjects.get(1)).addRandomShip((Ship) gameObjects.get(9));
		((Board) gameObjects.get(0)).toString();
		// System.out.println(((Board) gameObjects.get(1)).displayComputerBoard());
		/*
		 * CHEAT-CHEAT -CHEAT - CHEAT -CHEAT - CHEAT If you activate the upper command
		 * line and inactive the command below you can see the ships of the opponent
		 * when you attack
		 */
		System.out.println(((Board) gameObjects.get(0)).displayBoard());
		player1.getInfo();
		computer.getInfo();
		((Board) gameObjects.get(0)).getInfo();
		;
		((Board) gameObjects.get(1)).getInfo();

		// Getting info of the player, life count and also board and ship informations

		while (playerLife != 0 && computerLife != 0) {
			System.out.println("\nNow your Turn\n");
			player1.manuelAttack(((Board) gameObjects.get(1)));
			((Board) gameObjects.get(1)).isSunk(((Board) (gameObjects.get(1))).getComputerShips());
			System.out.println(((Board) (gameObjects.get(1))).displayComputerBoardHidden());
			System.out.println("Now the Computer's Turn");
			computer.randomAttack(((Board) ((gameObjects.get(0)))));
			((Board) gameObjects.get(0)).isSunk(((Board) gameObjects.get(0)).getYourShips());
			System.out.println(((Board) (gameObjects.get(0))).displayBoard());
		}
		/*
		 * We attack the ships on the coordination of the board. After each attack, if a
		 * ship gets sunken, the board show it and change its status and informations.
		 * Each shots the life of the player get decrease.If the life of the player gets
		 * zero so the game finish.
		 */
		if (this.getPlayerLife() == 0) {
			System.out.println("Sorry You lost. Computer Wins!!");
			System.out.println("--------------------------------");
		} else if (this.getComputerLife() == 0) {
			System.out.println("Congratulations You Win!!");
			System.out.println("--------------------------------");
		}
		playAgain();
	}

	// I use here a interface attackable. Player enter the coordinates manually in
	// here.
	// If we player write a wrong letter it goes into try catch block and wants to
	// change it with a correct one.
	public void manuelAttack(Attackable board) {

		boolean situation = true;
		while (situation == true) {
			try {

				board.manuelAttack();

				System.out.println(((Board) board).getName() + " is under Attack!!!");

				System.out.println("Which coordinates you want to attack?");
				System.out.println("Enter enter a letter for X coordinate (A-J): ");

				String letter = "";
				int number1 = 0;
				int number2 = 0;
				boolean condition = true;

				while (condition == true) {

					try {
						letter = sc.next();
						number1 = (int) (letter.toUpperCase().charAt(0)) - 65;
						if (number1 != 0 && number1 != 1 && number1 != 2 && number1 != 3 && number1 != 4 && number1 != 5
								&& number1 != 6 && number1 != 7 && number1 != 8 && number1 != 9) {

							throw new Exception();
						} else {
							System.out.println("Enter enter a Number for Y coordinate: (0-9)");
							number2 = sc.nextInt();
							condition = false;
						}
					} catch (Exception e) {
						System.out.println("Enterence is not valid, please enter a valid letter (A-J): ");
					}
				}

				if (((Board) board).getOcean()[number2][number1].getSquareStatus().equals(SquareStatus.SHIP)) {
					System.out.println("Yes you HIT!");

					((Board) board).getOcean()[number2][number1].setSquareStatus(SquareStatus.SHOT);
					computerLife = computerLife - 1;
					System.out.println("Computer's life is: " + computerLife);

				} else {
					System.out.println("You missed!");
					((Board) board).getOcean()[number2][number1].setSquareStatus(SquareStatus.MISS);
				}
			} catch (Exception e) {
				System.out.println(
						"Sorry but your missile went out of the board:)\nYou entered a wrong Y coordinate and lost your right. \n");
			}
			situation = false;
		}

	}

	// Randomly generate 2 numbers for x and y axis and make the same progress with
	// the attack method. If the numbers occur, then it checks it and generates new
	// numbers.
	// I use here a interface attackable.
	public void randomAttack(Attackable board) {

		board.manuelAttack();

		System.out.println(((Board) board).getName() + " is under Attack!!!");

		Random rnd = new Random();
		Random rnd2 = new Random();

		boolean condition = true;

		int number2 = 0;
		int number1 = 0;

		while (condition == true) {
			boolean condition2 = true;
			number2 = rnd.nextInt(10);
			number1 = rnd2.nextInt(10);

			int[][] items = new int[1][2];
			items[0][0] = number2;
			items[0][1] = number1;

			for (int i = 0; i < turn.size(); i++) {
				if (turn.get(i).equals(items)) {
					condition2 = false;
				}
			}
			if (condition2 == true) {
				turn.add(items);
				condition = false;
			}
		}

		if (((Board) board).getOcean()[number2][number1].getSquareStatus().equals(SquareStatus.SHIP)) {
			System.out.println("Computer HIT You!\n");
			((Board) board).getOcean()[number2][number1].setSquareStatus(SquareStatus.SHOT);
			playerLife = playerLife - 1;

			System.out.println("Your life is: " + playerLife);

		} else {
			System.out.println("Computer missed!\n");
			((Board) board).getOcean()[number2][number1].setSquareStatus(SquareStatus.MISS);
		}
	}

	// Giving information about game
	public void enterance() {

		System.out.println();
		System.out.println("First place your ships on the board.");
		System.out.println("Ocean: \"-\"");
		System.out.println("Ship: \"+\"");
		System.out.println("Hit: \"X\"");
		System.out.println("Miss: \"0\"\n");
		System.out.println("You have a frigate(Size:5), cruiser(Size:4), submarine(Size:3) and patrol boat(Size:2)!\n");
		System.out.println("Who get the ships of opponent sunken first, he/she win the game!");
		System.out.println("Now locate your ships!\n");

	}

	// Repeating game if we want
	public void playAgain() {

		System.out.println("Would you like to play again? (Y/N)");
		String situation = sc.next();
		if (situation.toUpperCase().equals("Y")) {
			gameStart();
		} else {
			System.out.println("BYE BYE!");
		}

	}

	// After creating object we add them into gameObjects to use each later.
	public void addObject(GameObject object) {
		this.gameObjects.add(object);

	}

	public ArrayList<int[][]> getTurn() {
		return turn;
	}

	public void setTurn(ArrayList<int[][]> turn) {
		this.turn = turn;
	}

	public ArrayList<GameObject> getGameObjects() {
		return gameObjects;
	}

	public void setGameObjects(ArrayList<GameObject> gameObjects) {
		this.gameObjects = gameObjects;
	}

	public void getInfo() {
		System.out.println("Player name: " + getName());
		System.out.println("Life: " + playerLife);

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Scanner getSc() {
		return sc;
	}

	public int getPlayerLife() {
		return playerLife;
	}

	public void setPlayerLife(int playerLife) {
		this.playerLife = playerLife;
	}

	public int getComputerLife() {
		return computerLife;
	}

	public void setComputerLife(int computerLife) {
		this.computerLife = computerLife;
	}

}
