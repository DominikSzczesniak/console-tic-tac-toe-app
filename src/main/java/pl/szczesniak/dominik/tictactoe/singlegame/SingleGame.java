package pl.szczesniak.dominik.tictactoe.singlegame;

import pl.szczesniak.dominik.tictactoe.exceptions.OtherPlayerTurnException;
import pl.szczesniak.dominik.tictactoe.exceptions.PlayerIsNotThePartOfTheGameException;
import pl.szczesniak.dominik.tictactoe.exceptions.SpotAlreadyTakenOnBoardException;
import pl.szczesniak.dominik.tictactoe.exceptions.SymbolIsUnsupportedException;

import java.util.Set;

public class SingleGame {

    private final Board board;
    private final Set<Symbol> supportedSymbols;
    private Player latestMoveByPlayer;
    private final Player playerOne;
    private final Player playerTwo;

    public SingleGame(Player playerOne, Player playerTwo) {
        board = new Board(3, 3);
        supportedSymbols = Set.of(new Symbol('X'), new Symbol('O'));
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }


    public Character[][] getBoardView() {
        return board.getCurrentState();
    }

    public void makeMove(final Player player, final PlayerMove move) {
        checkPlayerIsPartOfSingleGame(player);
        checkCanUsePlayerSymbol(player.getSymbol().getValue());
        checkIsPlayerTurn(player);
        checkIsSpotNotTaken(player, move);
        board.placeSymbol(player.getSymbol().getValue(), move.getRowIndex(), move.getColumnIndex());
        latestMoveByPlayer = player;
    }

    private void checkPlayerIsPartOfSingleGame(Player player) {
        if (!player.equals(playerOne) && !player.equals(playerTwo)) {
            throw new PlayerIsNotThePartOfTheGameException("Player " + player + " is not part of the game");
        }
    }

    private void checkCanUsePlayerSymbol(final char playerSymbol) {
        if (supportedSymbols.stream().noneMatch(symbol -> symbol.getValue() == playerSymbol)) {
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
        final char symbol = player.getSymbol().getValue();
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

    public boolean isDraw(Player playerOne, Player playerTwo) {
        final Character[][] drawArray = getBoardView();
        for (int i = 0; i < board.getRowsNumber(); i++) {
            for (int k = 0; k < board.getColumnNumber(); k++) {
                if (drawArray[i][k] == null && !checkIfPlayerWon(playerOne) && !checkIfPlayerWon(playerTwo)) {
                    return false;
                }
            }
        }
        return true;
    }
}
