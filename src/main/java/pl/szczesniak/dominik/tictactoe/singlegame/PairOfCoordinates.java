package pl.szczesniak.dominik.tictactoe.singlegame;

public class PairOfCoordinates {
    private final int row;
    private final int column;

    public PairOfCoordinates(final int row, final int column) {
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
