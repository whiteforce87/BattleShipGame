package battleShipGame;

public class Squares {

	// Board contains squares and they are the smallest part of the game. They have
	// coordinates and statuses.

	private SquareStatus squareStatus;
	private int x;
	private int y;

	public Squares(int x, int y, SquareStatus squareStatus) {
		super();
		this.squareStatus = squareStatus;
	}

	public SquareStatus getSquareStatus() {
		return squareStatus;
	}

	public void setSquareStatus(SquareStatus squareStatus) {
		this.squareStatus = squareStatus;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
