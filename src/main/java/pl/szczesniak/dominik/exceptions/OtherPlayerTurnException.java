package pl.szczesniak.dominik.exceptions;

public class OtherPlayerTurnException extends RuntimeException {
    public OtherPlayerTurnException(String message) {
        super(message);
    }
}
