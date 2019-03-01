/*
 * A single square on a Minesweeper board.
 */

public class Tile {
	// The character the tile appears as when revealed.
	private char display;

	// Whether the tile contains a mine.
	private boolean isMine;

	// Whether the tile is revealed.
	private boolean isRevealed;

	// Whether the tile is marked.
	private boolean isMarked;

	public Tile() {
		display    = ' ';
		isMine     = false;
		isRevealed = false;
		isMarked   = false;
	}

	// Return whether the Tile contains a mine.
	public boolean isMine() {
		return isMine;
	}

	// Turn tile into a mine.
	public void makeMine() {
		isMine = true;
		display = '@';
	}

	// Change the character the tile appears as when revealed.
	public void setDisplay(char ch) {
		display = ch;
	}

	// Get the character the tile appears as when revealed.
	public char getDisplay() {
		return display;
	}

	// Reveal the tile.
	public void reveal() {
		isRevealed = true;
	}

	// Return whether the tile is revealed.
	public boolean isRevealed() {
		return isRevealed;
	}

	// Toggle marked status of tile.
	public void toggleMark() {
		if (isMarked) {
			isMarked = false;
		}
		else {
			isMarked = true;
		}
	}

	// Return whether the tile is marked.
	public boolean isMarked() {
		return isMarked;
	}

	// Return true if tile appears as a blank tile when revealed.
	public boolean isBlank() {
		if (display == ' ') {
			return true;
		}
		else {
			return false;
		}
	}
}
