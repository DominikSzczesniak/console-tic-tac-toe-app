package pl.szczesniak.dominik.tictactoe.singlegame.domain.exceptions;

public class SpotAlreadyTakenOnBoardException extends RuntimeException {
    public SpotAlreadyTakenOnBoardException(String message) {
        super(message);
    }
}
