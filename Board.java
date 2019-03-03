/*
 * The board for a game of Minesweeper, composed of a 9x9 array of tiles.
 */

import java.util.Random;
import java.util.ArrayList;

public class Board {
	// 9x9 array of tiles.
	private Tile[][] tiles;

	// How many mines are on the game board.
	private int mines;

	public Board() {
		initTiles();
		populate();
		updateTileNumbers();
	}

	// Create array of tiles.
	private void initTiles() {
		tiles = new Tile[9][9];

		for (int i = 0; i < 9; ++i) {
			for (int j = 0; j < 9; ++j) {
				tiles[i][j] = new Tile();
			}
		}
	}

	// Display contents of board in console.
	public void display() {
		System.out.println("\n    A B C D E F G H I\n");
		for (int row = 0; row < 9; row++) {
			System.out.print((row + 1) + "   ");
			for (int column = 0; column < 9; column++) {
				if (tiles[row][column].isRevealed()) {
					System.out.print(tiles[row][column].getDisplay() + " ");
				}
				else {
					if (tiles[row][column].isMarked()) {
						System.out.print("! ");
					}
					else {
						System.out.print("# ");
					}
				}
			}
			System.out.print("  " + (row + 1) + "\n");
		}
		System.out.println("\n    A B C D E F G H I\n");
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
			// Randomly pick two numbers from 0 to 8
			// Check if there is a mine on that tile
			int randomColumn = random.nextInt(9);
			int randomRow    = random.nextInt(9);

			if (!tiles[randomRow][randomColumn].isMine()) {
				tiles[randomRow][randomColumn].makeMine();
				minesLeftToPlace--;
			}
		}
	}

	// Give each tile a display value indicating the number of mines
	// surrounding it.
	private void updateTileNumbers() {
		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				if (!tiles[row][column].isMine()) {
					int mineCount = 0;

					Coordinate north = new Coordinate(column, row - 1);
					if (onBoard(north)) {
						if (tiles[north.y][north.x].isMine()) {
							mineCount++;
						}
					}

					Coordinate northEast = new Coordinate(column + 1, row - 1);
					if (onBoard(northEast)) {
						if (tiles[northEast.y][northEast.x].isMine()) {
							mineCount++;
						}
					}

					Coordinate east = new Coordinate(column + 1, row);
					if (onBoard(east)) {
						if (tiles[east.y][east.x].isMine()) {
							mineCount++;
						}
					}

					Coordinate southEast = new Coordinate(column + 1, row + 1);
					if (onBoard(southEast)) {
						if (tiles[southEast.y][southEast.x].isMine()) {
							mineCount++;
						}
					}

					Coordinate south = new Coordinate(column, row + 1);
					if (onBoard(south)) {
						if (tiles[south.y][south.x].isMine()) {
							mineCount++;
						}
					}

					Coordinate southWest = new Coordinate(column - 1, row + 1);
					if (onBoard(southWest)) {
						if (tiles[southWest.y][southWest.x].isMine()) {
							mineCount++;
						}
					}

					Coordinate west = new Coordinate(column - 1, row);
					if (onBoard(west)) {
						if (tiles[west.y][west.x].isMine()) {
							mineCount++;
						}
					}

					Coordinate northWest = new Coordinate(column - 1, row - 1);
					if (onBoard(northWest)) {
						if (tiles[northWest.y][northWest.x].isMine()) {
							mineCount++;
						}
					}

					if (mineCount != 0) {
						tiles[row][column].setDisplay((char)(mineCount + '0'));
					}
				}
			}
		}
	}

	// Mark or unmark the tile at the passed Coordinate.
	public void markTile(Coordinate chosen) {
		tiles[chosen.y][chosen.x].toggleMark();
	}

	// Reveal tile at passed coordinate and all connected tiles that are blank.
	public void revealTiles(Coordinate chosen) {
		ArrayList<Coordinate> toSearch = new ArrayList<Coordinate>();

		tiles[chosen.y][chosen.x].reveal();
		if (tiles[chosen.y][chosen.x].isBlank()) {
			toSearch.add(chosen);

			while (toSearch.size() != 0) {
				Coordinate center = new Coordinate(toSearch.get(0));

				Coordinate north = new Coordinate(center.x, center.y - 1);
				boolean northValidAndBlank = reveal(north);
				if (northValidAndBlank)
					toSearch.add(north);

				Coordinate northEast = new Coordinate(center.x + 1, center.y - 1);
				boolean northEastValidAndBlank = reveal(northEast);
				if (northEastValidAndBlank)
					toSearch.add(northEast);

				Coordinate east = new Coordinate(center.x + 1, center.y);
				boolean eastValidAndBlank = reveal(east);
				if (eastValidAndBlank)
					toSearch.add(east);

				Coordinate southEast = new Coordinate(center.x + 1, center.y + 1);
				boolean southEastValidAndBlank = reveal(southEast);
				if (southEastValidAndBlank)
					toSearch.add(southEast);

				Coordinate south = new Coordinate(center.x, center.y + 1);
				boolean southValidAndBlank = reveal(south);
				if (southValidAndBlank)
					toSearch.add(south);

				Coordinate southWest = new Coordinate(center.x - 1, center.y + 1);
				boolean southWestValidAndBlank = reveal(southWest);
				if (southWestValidAndBlank)
					toSearch.add(southWest);

				Coordinate west = new Coordinate(center.x - 1, center.y);
				boolean westValidAndBlank = reveal(west);
				if (westValidAndBlank)
					toSearch.add(west);

				Coordinate northWest = new Coordinate(center.x - 1, center.y - 1);
				boolean northWestValidAndBlank = reveal(northWest);
				if (northWestValidAndBlank)
					toSearch.add(northWest);

				toSearch.remove(0);
			}
		}
	}

	// Reveal tile if on board and return true if tile is blank or false when
	// tile is not blank, already revealed, or is not on the board.
	private boolean reveal(Coordinate chosen) {
		if (onBoard(chosen)) {
			Tile tile = tiles[chosen.y][chosen.x];

			if (tile.isRevealed()) {
				return false;
			}
			else {
				tile.reveal();
			}

			if (tile.isBlank()) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	// Returns true if tile is on board.
	private boolean onBoard(Coordinate tile) {
		int rows    = 9;
		int columns = 9;

		boolean rowOnBoard = tile.y >= 0 && tile.y < rows;
		boolean columnOnBoard = tile.x >= 0 && tile.x < columns;

		if (rowOnBoard && columnOnBoard) {
			return true;
		}
		else {
			return false;
		}
	}

	// Returns true if tile at passed Coordinate is revealed.
	public boolean tileIsRevealed(Coordinate tile) {
		return tiles[tile.y][tile.x].isRevealed();
	}
}
