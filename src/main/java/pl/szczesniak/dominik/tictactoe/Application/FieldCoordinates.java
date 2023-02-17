package pl.szczesniak.dominik.tictactoe.Application;

public class FieldCoordinates {
    private final int row;
    private final int column;

    public FieldCoordinates(int row, int column) {
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
