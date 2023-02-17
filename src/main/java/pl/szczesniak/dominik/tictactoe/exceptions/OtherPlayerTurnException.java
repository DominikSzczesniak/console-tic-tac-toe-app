package pl.szczesniak.dominik.tictactoe.exceptions;

public class OtherPlayerTurnException extends RuntimeException {
    public OtherPlayerTurnException(String message) {
        super(message);
    }
}
