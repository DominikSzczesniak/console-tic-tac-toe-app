package pl.szczesniak.dominik.singlegame;

import pl.szczesniak.dominik.exceptions.OtherPlayerTurnException;
import pl.szczesniak.dominik.exceptions.SpotAlreadyTakenOnBoardException;
import pl.szczesniak.dominik.exceptions.SymbolIsUnsupportedException;
import pl.szczesniak.dominik.tictactoe.Board;
import pl.szczesniak.dominik.tictactoe.Player;
import pl.szczesniak.dominik.tictactoe.PlayerMove;
import pl.szczesniak.dominik.tictactoe.Symbol;

import java.util.Set;

public class SingleGame {

    private final Board board;
    private final Set<Symbol> supportedSymbols;

    private Player latestMoveByPlayer;

    public SingleGame() {
        board = new Board(3, 3);
        supportedSymbols = Set.of(new Symbol('X'), new Symbol('O'));
    }


    public Character[][] getBoardView() {
        return board.getCurrentState();
    }

    public void makeMove(final Player player, final PlayerMove move) {
        checkCanUsePlayerSymbol(player.getSymbol());
        checkIsPlayerTurn(player);
        checkIsSpotNotTaken(player, move);
        board.placeSymbol(player.getSymbol(), move.getRowIndex(), move.getColumnIndex());
        latestMoveByPlayer = player;
    }

    private void checkCanUsePlayerSymbol(final char playerSymbol) {
        if (supportedSymbols.stream().noneMatch(symbol -> symbol.getSymbol() == playerSymbol)) {
            throw new SymbolIsUnsupportedException("Symbol " + playerSymbol + " is unsupported.");
        }
    }

    private void checkIsPlayerTurn(final Player player) {
        if (latestMoveByPlayer != null && latestMoveByPlayer.equals(player)) {
            throw new OtherPlayerTurnException("Player " + player + " can't make more than one turn in a row");
        }
    }

    private void checkIsSpotNotTaken(final Player player, final PlayerMove move) {
        if (board.isSpotTaken(move.getRowIndex(), move.getColumnIndex())) {
            throw new SpotAlreadyTakenOnBoardException("Player " + player + " can't make a move. Spot is already taken.");
        }
    }

    public boolean checkIfPlayerWon(final Player player) {
        final char symbol = player.getSymbol();
        final Character[][] currentState = getBoardView();
        final Character[][] winningLine = {{null, null, null}, {null, null, null}, {null, null, null}};
        for (int i = 0; i < 8; i++) {
            switch (i) {
                case 0:
                    winningLine[0][0] = symbol;
                    winningLine[0][1] = symbol;
                    winningLine[0][2] = symbol;
                    if (currentState[0][0] == winningLine[0][0] && currentState[0][1] == winningLine[0][1] && currentState[0][2] == winningLine[0][2]) {
                        return true;
                    }
                    break;
                case 1:
                    winningLine[1][0] = symbol;
                    winningLine[1][1] = symbol;
                    winningLine[1][2] = symbol;
                    if (currentState[1][0] == winningLine[1][0] && currentState[1][1] == winningLine[1][1] && currentState[1][2] == winningLine[1][2]) {
                        return true;
                    }
                    break;
                case 2:
                    winningLine[0][0] = symbol;
                    winningLine[1][0] = symbol;
                    winningLine[2][0] = symbol;
                    if (currentState[0][0] == winningLine[0][0] && currentState[1][0] == winningLine[1][0] && currentState[2][0] == winningLine[2][0]) {
                        return true;
                    }
                    break;
                case 3:
                    winningLine[0][1] = symbol;
                    winningLine[1][1] = symbol;
                    winningLine[2][1] = symbol;
                    if (currentState[0][1] == winningLine[0][1] && currentState[1][1] == winningLine[1][1] && currentState[2][1] == winningLine[2][1]) {
                        return true;
                    }
                    break;
                case 4:
                    winningLine[0][2] = symbol;
                    winningLine[1][2] = symbol;
                    winningLine[2][2] = symbol;
                    if (currentState[0][2] == winningLine[0][2] && currentState[1][2] == winningLine[1][2] && currentState[2][2] == winningLine[2][2]) {
                        return true;
                    }
                    break;
                case 5:
                    winningLine[0][0] = symbol;
                    winningLine[1][1] = symbol;
                    winningLine[2][2] = symbol;
                    if (currentState[0][0] == winningLine[0][0] && currentState[1][1] == winningLine[1][1] && currentState[2][2] == winningLine[2][2]) {
                        return true;
                    }
                    break;
                case 6:
                    winningLine[0][2] = symbol;
                    winningLine[1][1] = symbol;
                    winningLine[2][0] = symbol;
                    if (currentState[0][2] == winningLine[0][2] && currentState[1][1] == winningLine[1][1] && currentState[2][0] == winningLine[2][0]) {
                        return true;
                    }
                    break;
                case 7:
                    winningLine[2][0] = symbol;
                    winningLine[2][1] = symbol;
                    winningLine[2][2] = symbol;
                    if (currentState[0][2] == winningLine[0][2] && currentState[1][1] == winningLine[1][1] && currentState[2][0] == winningLine[2][0]) {
                        return true;
                    }
            }
        }
        return false;
    }

    public boolean isDraw() {
        final Character[][] drawArray = getBoardView();
        for (int i = 0; i < board.getRowsNumber(); i++) {
            for (int k = 0; k < board.getColumnNumber(); k++) {
                if (drawArray[i][k] == null) {
                    System.out.println("Game is still not over");
                    return false;
                }
            }
        }
        return true;
    }
}
