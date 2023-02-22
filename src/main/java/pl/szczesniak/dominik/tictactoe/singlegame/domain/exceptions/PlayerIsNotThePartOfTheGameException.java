package pl.szczesniak.dominik.tictactoe.singlegame.domain.exceptions;

public class PlayerIsNotThePartOfTheGameException extends RuntimeException {
    public PlayerIsNotThePartOfTheGameException(String message) {
        super(message);
    }
}
