package pl.szczesniak.dominik.tictactoe.Application;

import pl.szczesniak.dominik.tictactoe.singlegame.*;

import java.util.Scanner;

public class TicTacToeApp {

    public static void main(String[] args) {

        Player playerOne = new Player(new Symbol('O'));
        Player playerTwo = new Player(new Symbol('X'));
        SingleGame game = new SingleGame(playerOne, playerTwo);
        Scanner scan = new Scanner(System.in);
        FieldNumberTranslator translator = new FieldNumberTranslator();

        GameResult latestResult;
        Player nextPlayer = playerOne;

        do {
            FieldCoordinates coordinates = translator.toCoordinates(getNumber(scan));
            latestResult = game.makeMove(nextPlayer, new PlayerMove(coordinates.getRow(), coordinates.getColumn()));
            nextPlayer = nextPlayer == playerTwo ? playerOne : playerTwo;
        } while (latestResult.getGameStatus().equals(GameStatus.IN_PROGRESS));
    }

    private static int getNumber(Scanner scanner) {
        return Integer.parseInt(scanner.nextLine());
    }
}
