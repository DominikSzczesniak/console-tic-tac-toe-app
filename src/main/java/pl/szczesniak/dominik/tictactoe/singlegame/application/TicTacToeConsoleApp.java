package pl.szczesniak.dominik.tictactoe.singlegame.application;

import pl.szczesniak.dominik.tictactoe.singlegame.domain.SingleGame;
import pl.szczesniak.dominik.tictactoe.singlegame.domain.exceptions.SpotAlreadyTakenOnBoardException;
import pl.szczesniak.dominik.tictactoe.singlegame.domain.model.GameResult;
import pl.szczesniak.dominik.tictactoe.singlegame.domain.model.GameStatus;
import pl.szczesniak.dominik.tictactoe.singlegame.domain.model.Player;
import pl.szczesniak.dominik.tictactoe.singlegame.domain.model.PlayerMove;
import pl.szczesniak.dominik.tictactoe.singlegame.domain.model.PlayerName;
import pl.szczesniak.dominik.tictactoe.singlegame.domain.model.Symbol;

import java.util.Scanner;

public class TicTacToeConsoleApp {
	public void run() {
		final Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to a single game of tic tac toe.");
		System.out.println("Player 1, choose your nickname");
		final Player playerOne = new Player(new Symbol('O'), new PlayerName(scan.nextLine()));
		System.out.println("Player 2, choose your nickname");
		final Player playerTwo = new Player(new Symbol('X'), new PlayerName(scan.nextLine()));
		final SingleGame game = new SingleGame(playerOne, playerTwo);
		final BoardPrinter printer = new BoardPrinter();
		final FieldNumberTranslator translator = new FieldNumberTranslator();
		int playAgain;


		printer.printBoardWithNumbers(game.getBoardView());
		GameResult latestResult;
		Player nextPlayer = playerOne;
		do {
			printer.printBoard(game.getBoardView());
			System.out.println(nextPlayer.getName() + " with symbol: " + nextPlayer.getSymbol() + ", to move");
			latestResult = makeMove(game, scan, translator, nextPlayer);
			nextPlayer = nextPlayer == playerTwo ? playerOne : playerTwo;
		} while (latestResult.getGameStatus().equals(GameStatus.IN_PROGRESS));

		printer.printBoard(game.getBoardView());
		printResultOfTheGame(latestResult);

		System.out.println("Would you like to play again? (1 - yes, 2 - no)");
		playAgain = scan.nextInt();
		if (playAgain == 1) {
			resetBoard(game.getBoardView());
			run();
		} else {
			System.out.println("Thanks for playing");
		}
	}

	private GameResult makeMove(SingleGame game, Scanner scan, FieldNumberTranslator translator, Player nextPlayer) {
		final FieldCoordinates coordinates = translator.toCoordinates(getNumber(scan));
		try {
			return game.makeMove(nextPlayer, new PlayerMove(coordinates.getRow(), coordinates.getColumn()));
		} catch (SpotAlreadyTakenOnBoardException exception) {
			System.out.println("Spot is taken, choose different number");
			return makeMove(game, scan, translator, nextPlayer);
		}
	}

	private void printResultOfTheGame(final GameResult result) {
		if (GameStatus.WIN.equals(result.getGameStatus())) {
			System.out.println("Congratulations, " + result.getWhoWon() + " won the round.");
		} else if (GameStatus.DRAW.equals(result.getGameStatus())) {
			System.out.println("It's a draw.");
		} else {
			throw new IllegalArgumentException("Cannot printResultOfTheGame when game is in status:" + result.getGameStatus());
		}
	}

	private void resetBoard(Character[][] board) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = ' ';
			}
		}
	}

	private int getNumber(Scanner scanner) {
		return Integer.parseInt(scanner.nextLine());
	}
}
