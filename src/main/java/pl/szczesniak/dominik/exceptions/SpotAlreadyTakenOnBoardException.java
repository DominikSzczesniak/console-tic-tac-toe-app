package pl.szczesniak.dominik.exceptions;

public class SpotAlreadyTakenOnBoardException extends RuntimeException {
    public SpotAlreadyTakenOnBoardException(String message) {
        super(message);
    }
}
