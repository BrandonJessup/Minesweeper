/*
 * A simple container for an x and y value.
 */

public class Coordinate {
	public int x;
	public int y;

	public Coordinate() {
		x = 0;
		y = 0;
	}

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Coordinate(Coordinate c) {
		this.x = c.x;
		this.y = c.y;
	}
}
