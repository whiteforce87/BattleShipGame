package battleShipGame;

public abstract class GameObject {

	// Game object has board, ships and related squares. Each object had their own
	// name and size.

	private int size;
	private String name;

	public GameObject(int size, String name) {
		super();
		this.size = size;
		this.name = name;
	}

	public abstract void getInfo();

	@Override
	public String toString() {
		return "GameObject are set and ready to play!!\n";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameObject other = (GameObject) obj;
		return size == other.size;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
