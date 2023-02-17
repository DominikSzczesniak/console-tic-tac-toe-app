package pl.szczesniak.dominik.tictactoe.singlegame;

public class PlayerMove {

    private final int rowIndex;
    private final int columnIndex;

    public PlayerMove(final int rowIndex, final int columnIndex) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }
}
