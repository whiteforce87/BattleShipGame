package battleShipGame;

public enum SquareStatus {
	// Every square has a status sign. It gets the value as ENUM. Every ship start
	// with "Ship" sign. And other places are as "Ocean" sign.
	// If one of the ships get shot, dependent square sign gets changed.
	OCEAN("-"), SHIP("+"), SHOT("X"), MISS("0"), SUNKEN("S");

	private String sign;

	private SquareStatus(String sign) {
		this.sign = sign;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

}
