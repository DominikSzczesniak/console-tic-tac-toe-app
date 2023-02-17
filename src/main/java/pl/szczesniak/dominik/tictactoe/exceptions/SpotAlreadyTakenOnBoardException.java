package pl.szczesniak.dominik.tictactoe.exceptions;

public class SpotAlreadyTakenOnBoardException extends RuntimeException {
    public SpotAlreadyTakenOnBoardException(String message) {
        super(message);
    }
}
