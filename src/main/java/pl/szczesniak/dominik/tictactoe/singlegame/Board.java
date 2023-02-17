package pl.szczesniak.dominik.tictactoe.singlegame;

public class Board {
    private final Character[][] currentState;
    private final int rowsNumber;
    private final int columnNumber;


    public Board(final int rowsNumber, final int columnNumber) {
        this.rowsNumber = rowsNumber;
        this.columnNumber = columnNumber;
        currentState = new Character[rowsNumber][columnNumber];
    }

    public Character[][] getCurrentState() {
        return deepCopy(currentState);
    }

    public void placeSymbol(final char symbol, final int rowIndex, final int columnIndex) {
        currentState[rowIndex][columnIndex] = symbol;
    }

    public int getRowsNumber() {
        return rowsNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public boolean isSpotTaken(final int rowIndex, final int columnIndex) {
        return currentState[rowIndex][columnIndex] != null;
    }

    private static Character[][] deepCopy(Character[][] original) {
        Character[][] copy = new Character[original.length][original[0].length];
        for (int x = 0; x < original.length; x++) {
            for (int y = 0; y < original[0].length; y++) {
                copy[x][y] = original[x][y];
            }
        }
        return copy;
    }

    public boolean hasSymbolOnFields(char symbol, PairOfCoordinates first, PairOfCoordinates second, PairOfCoordinates third) {


        return currentState[first.getRow()][first.getColumn()] != null && currentState[first.getRow()][first.getColumn()] == symbol &&
                currentState[second.getRow()][second.getColumn()] != null && currentState[second.getRow()][second.getColumn()]== symbol &&
                currentState[third.getRow()][third.getColumn()] != null && currentState[third.getRow()][third.getColumn()]== symbol;
    }
}
