package pl.szczesniak.dominik.tictactoe.Application;

import pl.szczesniak.dominik.tictactoe.singlegame.*;
import java.util.Scanner;

public class TicTacToeApp {

    public static void main(String[] args) {

        System.out.println("Welcome to a single game of tic tac toe.");
        Player playerOne = new Player(new Symbol('O'));
        Player playerTwo = new Player(new Symbol('X'));
        SingleGame game = new SingleGame(playerOne, playerTwo);
        BoardPrinter printer = new BoardPrinter();
        Scanner scan = new Scanner(System.in);
        FieldNumberTranslator translator = new FieldNumberTranslator();

        GameResult latestResult;
        Player nextPlayer = playerOne;

        do {
            System.out.println(nextPlayer.getSymbol() + " to move");
            printer.printBoard(game.getBoardView());
            FieldCoordinates coordinates = translator.toCoordinates(getNumber(scan));
            latestResult = game.makeMove(nextPlayer, new PlayerMove(coordinates.getRow(), coordinates.getColumn()));
            nextPlayer = nextPlayer == playerTwo ? playerOne : playerTwo;
        } while (latestResult.getGameStatus().equals(GameStatus.IN_PROGRESS));

        printer.printBoard(game.getBoardView());
        System.out.println("Congratulations, " + nextPlayer.getSymbol() + " won the game.");

    }

    private static int getNumber(Scanner scanner) {
        return Integer.parseInt(scanner.nextLine());
    }
}
