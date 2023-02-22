package pl.szczesniak.dominik.tictactoe.singlegame.domain.exceptions;

public class OtherPlayerTurnException extends RuntimeException {
    public OtherPlayerTurnException(String message) {
        super(message);
    }
}
