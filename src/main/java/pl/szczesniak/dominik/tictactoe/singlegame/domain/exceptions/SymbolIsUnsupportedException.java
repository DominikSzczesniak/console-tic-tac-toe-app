package pl.szczesniak.dominik.tictactoe.singlegame.domain.exceptions;

public class SymbolIsUnsupportedException extends RuntimeException {
    public SymbolIsUnsupportedException(String message) {
        super(message);
    }
}
