package pl.szczesniak.dominik.tictactoe;

public class PlayerMove {

    private final int rowIndex;
    private final int columnIndex;
    public PlayerMove(int rowIndex, int columnIndex) {
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
