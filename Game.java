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

		if (response == "start") {
			gameLoop();
		}

		printProgramExit();
	}

	// The main loop of the game.
	private void gameLoop() {
		// TOOD
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
		while (!response.equals("start") && !response.equals("exit")) {
			System.out.println("Enter 'start' to start a new game, 'exit' to exit, or 'help' to list in-game controls.");
			System.out.print("> ");
			response = input.nextLine();

			boolean valid = response.equals("start") || response.equals("exit") || response.equals("help");
			if (!valid) {
				System.err.println("Please enter a valid option.\n");
			}
			else if (response.equals("help")) {
				printHelp();
			}
		}

		return response;
	}

	// Display a list of valid inputs to the user.
	private void printHelp() {
		// TODO
	}

	// Display a message on program exit thanking the user for playing the game.
	private void printProgramExit() {
		System.out.println("\nThank you for playing!");
	}
}
