package pl.szczesniak.dominik.tictactoe.exceptions;

public class SymbolIsUnsupportedException extends RuntimeException {
    public SymbolIsUnsupportedException(String message) {
        super(message);
    }
}
