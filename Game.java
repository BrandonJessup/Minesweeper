/*
 * A game of Minesweeper.
 */

import java.util.Scanner;

public class Game {
	// Display the title screen and launch into the game should the user
	// request to.
	public void start() {
		printTitle();
		String response = titlePrompt();

		if (response.contentEquals("start")) {
			gameLoop();
		}

		printProgramExit();
	}

	// The main loop of the game.
	private void gameLoop() {
		String response = "";
		while (!response.contentEquals("exit")) {
			// TODO: Display game board.
			response = turnPrompt();
			// TODO: Handle validated user input.
		}
	}

	// Display the text-art title of the game.
	private void printTitle() {
		System.out.println("#   #  #####  #   #  #####   ####  #   #  #####  #####  ####   #####  ####");
		System.out.println("## ##    #    ##  #  #      #      #   #  #      #      #   #  #      #   #");
		System.out.println("# # #    #    # # #  ##      ###   # # #  ##     ##     ####   ##     ####");
		System.out.println("#   #    #    #  ##  #          #  # # #  #      #      #      #      #   #");
		System.out.println("#   #  #####  #   #  #####  ####    # #   #####  #####  #      #####  #   #\n");
	}

	// Prompt user for whether they would like to start a game of Minesweeper,
	// exit, or display the help menu
	private String titlePrompt() {
		Scanner input = new Scanner(System.in);

		String response = "";
		while (!response.contentEquals("start") && !response.contentEquals("exit")) {
			System.out.println("Enter 'start' to start a new game, 'exit' to exit, or 'help' to list in-game controls.");
			System.out.print("> ");
			response = input.nextLine();

			boolean valid = response.contentEquals("start") || response.contentEquals("exit") || response.contentEquals("help");
			if (!valid) {
				System.out.println("Please enter a valid option.\n");
			}
			else if (response.contentEquals("help")) {
				printHelp();
			}
		}

		return response;
	}

	// Display a list of valid inputs to the user.
	private void printHelp() {
		System.out.println("\nCommand list:");
		System.out.println("A1   - Where 'A' is a letter from A-I and '1' is a number from 1-9. Scan a tile.");
		System.out.println("!A1  - Mark or unmark a tile.");
		System.out.println("exit - Quit the game.");
		System.out.println("help - Show this menu again.\n");
	}

	// Display a message on program exit thanking the user for playing the game.
	private void printProgramExit() {
		System.out.println("\nThank you for playing!");
	}

	// Ask the user for an input and validate it. Returns the user's move for
	// the turn as a string.
	private String turnPrompt() {
		String response = "";

		Scanner input = new Scanner(System.in);
		boolean valid = false;
		while (!valid || response.contentEquals("help")) {
			System.out.print("> ");
			response = input.nextLine();

			if (response.contentEquals("exit")) {
				valid = true;
			}
			else if (response.contentEquals("help")) {
				valid = true;
				printHelp();
			}
			else if (isTile(response)) {
				valid = true;
			}
			else {
				valid = false;
				System.out.println("Input not valid! Enter 'help' if needed.\n");
			}
		}

		return response;
	}

	// Takes a String and returns true if the content is a tile on the
	// board (A7) or an exclamation point followed by a tile (!B3).
	private boolean isTile(String str) {
		boolean isTile;

		int length = str.length();
		// Check if user entered a valid scan tile command.
		if (length == 2) {
			if (isColumn(str.charAt(0)) && isRow(str.charAt(1))) {
				isTile = true;
			}
			else {
				isTile = false;
			}
		}
		// Check if user entered a valid mark tile command.
		else if (length == 3) {
			if (str.charAt(0) == '!' && isColumn(str.charAt(1)) && isRow(str.charAt(2))) {
				isTile = true;
			}
			else {
				isTile = false;
			}
		}
		else {
			isTile = false;
		}

		return isTile;
	}

	// Takes a character and returns true if the character is within the valid
	// range for columns.
	private boolean isColumn(char ch) {
		boolean isColumn;

		char upper = Character.toUpperCase(ch);

		switch (upper) {
		case 'A':
		case 'B':
		case 'C':
		case 'D':
		case 'E':
		case 'F':
		case 'G':
		case 'H':
		case 'I':
			isColumn = true;
			break;
		default:
			isColumn = false;
			break;
		}

		return isColumn;
	}

	// Takes a character and returns true if that character is within the valid
	// range for rows.
	private boolean isRow(char ch) {
		boolean isRow = false;

		switch (ch) {
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9':
			isRow = true;
			break;
		default:
			isRow = false;
			break;
		}

		return isRow;
	}
}
