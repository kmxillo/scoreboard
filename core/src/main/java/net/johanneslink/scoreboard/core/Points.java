package net.johanneslink.scoreboard.core;

public enum Points {
	Two(2), Three(3), One(1), MinusOne(-1);

	private final int value;

	Points(int value) {
		this.value = value;
	}

	public int useToChange(int number) {
		return Math.max(0, number + value);
	}
}
