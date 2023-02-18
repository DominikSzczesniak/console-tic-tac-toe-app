package pl.szczesniak.dominik.tictactoe.Application;

public class FieldCoordinates {
    private final int row;
    private final int column;

    public FieldCoordinates(final int row, final int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
