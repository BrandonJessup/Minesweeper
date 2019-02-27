/*
 * The board for a game of Minesweeper, composed of a 9x9 array of tiles.
 */

import java.util.Random;

public class Board {
	// 9x9 array of tiles.
	private Tile[][] tiles;

	// How many mines are on the game board.
	private int mines;

	public Board() {
		tiles = new Tile[9][9];
		populate();
		updateTileNumbers();
	}

	// Returns number of mines on the board less the number of marked tiles.
	public int mineCount() {
		int mineCount = mines;

		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				if (tiles[row][column].isMarked()) {
					mineCount--;
				}
			}
		}

		return mineCount;
	}

	// Toggles marked status on indicated tile. Returns false if tile is
	// already revealed.
	public boolean toggleMark(Coordinate c) {
		if (tiles[c.y][c.x].isRevealed()) {
			return false;
		}
		else {
			tiles[c.y][c.x].toggleMark();
			return true;
		}
	}

	// Distribute ten mines among random tiles.
	private void populate() {
		mines = 10;

		Random random = new Random();
		int minesLeftToPlace = mines;
		while (minesLeftToPlace > 0) {
			// Randomly pick two numbers from 1 to 9
			// Check if there is a mine on that tile
			int randomColumn = random.nextInt(9) + 1;
			int randomRow    = random.nextInt(9) + 1;

			if (!tiles[randomRow][randomColumn].isMine()) {
				tiles[randomRow][randomColumn].makeMine();
				minesLeftToPlace--;
			}
		}
	}

	// Give each tile a display value indicating the number of mines
	// surrounding it.
	private void updateTileNumbers() {
		// TODO: Write body of function.
	}
}
