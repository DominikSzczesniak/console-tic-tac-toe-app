package pl.szczesniak.dominik.tictactoe.singlegame.application;

import pl.szczesniak.dominik.tictactoe.gamehistory.adapter.AddingNewLinesToFileGameHistoryStorage;
import pl.szczesniak.dominik.tictactoe.gamehistory.adapter.FilePerPlayerGameHistoryStorage;
import pl.szczesniak.dominik.tictactoe.gamehistory.adapter.OverwritingFileGameHistoryStorage;
import pl.szczesniak.dominik.tictactoe.gamehistory.domain.GameHistoryService;
import pl.szczesniak.dominik.tictactoe.gamehistory.domain.SingleGameResult;
import pl.szczesniak.dominik.tictactoe.player.model.PlayerName;
import pl.szczesniak.dominik.tictactoe.singlegame.domain.exceptions.IncorrectPlayerNameException;
import pl.szczesniak.dominik.tictactoe.singlegame.domain.GameHistoryHandler;
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

	private final GameHistoryService gameHistoryService = new GameHistoryService(new OverwritingFileGameHistoryStorage("Game_history.txt"));
	private final GameHistoryService gameHistoryService = new GameHistoryService(new OverwritingFileGameHistoryStorage());
	private final Scanner scan = new Scanner(System.in);
	private final Symbol SYMBOL_O = new Symbol('O');

	GameHistoryHandler gameHistoryHandler = new GameHistoryHandler();
	final Scanner scan = new Scanner(System.in);
	private final Player playerOne;
	private final Player playerTwo;
	private int playAgain;
	private final FieldNumberTranslator translator = new FieldNumberTranslator();

	public TicTacToeConsoleApp() {
		System.out.println();
		System.out.println("|----------------------------------------|");
		System.out.println("|Welcome to a single game of tic tac toe.|");
		System.out.println("|----------------------------------------|");
		System.out.println();
		System.out.println("-------------------------------------------------------------------------------------------------------");
		System.out.println("|The game consists of 2 players, each with a symbol - X or O, player with symbol O gets to move first.|");
		System.out.println("-------------------------------------------------------------------------------------------------------");
		System.out.println();
	}

	public void run() {
		System.out.println("---------------------------");
		System.out.println("Player 1, choose your name:");
		System.out.println("---------------------------");
		PlayerName playerNameOne = setPlayerName(scan);
		System.out.println(playerNameOne.getValue() + " choose your symbol, enter O or X");
		Player playerOne = new Player(new Symbol(getSymbol(scan)), playerNameOne);

		System.out.println("---------------------------");
		System.out.println("Player 2, choose your name");
		System.out.println("---------------------------");
		PlayerName playerNameTwo = setPlayerName(scan);
		Player playerTwo;
		if (playerOne.getSymbol().equals(SYMBOL_O)) {
			playerTwo = new Player(new Symbol('X'), playerNameTwo);
		} else {
			playerTwo = new Player(SYMBOL_O, playerNameTwo);
		}

		System.out.println("Enter size of the board 3-6");
		int boardSize = Integer.parseInt(scan.nextLine());

		new TicTacToeGame(playerOne, playerTwo, boardSize).play();

		printer.printBoard(game.getBoardView());
		printResultOfTheGame(latestResult);
		SingleGameResult singleGameResult = new SingleGameResult(latestResult.getWhoWon());
		gameHistoryService.store(singleGameResult);
		System.out.println("Number of " + latestResult.getWhoWon() + "'s wins: " + gameHistoryService.loadPlayerScore(latestResult.getWhoWon()).getValue());

		System.out.println("Do you want to make a new setup? 1 - yes, 2 - no");
		int choice = Integer.parseInt(scan.nextLine());
		if (choice == 1) {
			run();
		} else {
			System.out.println("-------------------");
			System.out.println("Thanks for playing!");
			System.out.println("-------------------");
		}
	}

	private PlayerName setPlayerName(final Scanner scanner) {
		try {
			return new PlayerName(scanner.nextLine());
		} catch (IncorrectPlayerNameException exception) {
			System.out.println("Name can only contain letters and must be 1-25 characters long.");
			return setPlayerName(scanner);
		}
	}

	private char getSymbol(final Scanner scanner) {
		final String symbol = scanner.nextLine();
		return symbol.charAt(0);
	}
}
