package pl.szczesniak.dominik.tictactoe.exceptions;

public class PlayerIsNotThePartOfTheGameException extends RuntimeException {
    public PlayerIsNotThePartOfTheGameException(String message) {
        super(message);
    }
}
