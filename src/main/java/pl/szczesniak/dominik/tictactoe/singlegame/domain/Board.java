package pl.szczesniak.dominik.tictactoe.singlegame.domain;

import pl.szczesniak.dominik.tictactoe.singlegame.domain.model.PairOfCoordinates;

class Board {
    private final Character[][] currentState;
    private final int rowsNumber;
    private final int columnNumber;

    public Board(final int rowsNumber, final int columnNumber) {
        this.rowsNumber = rowsNumber;
        this.columnNumber = columnNumber;
        currentState = new Character[rowsNumber][columnNumber];
    }

    void placeSymbol(final char symbol, final int rowIndex, final int columnIndex) {
        currentState[rowIndex][columnIndex] = symbol;
    }

    boolean isSpotTaken(final int rowIndex, final int columnIndex) {
        return currentState[rowIndex][columnIndex] != null;
    }

    boolean hasSymbolOnFields(char symbol, PairOfCoordinates first, PairOfCoordinates second, PairOfCoordinates third) {
        return hasSymbolAtCoordinates(symbol, first)
                && hasSymbolAtCoordinates(symbol, second)
                && hasSymbolAtCoordinates(symbol, third);
    }

    private boolean hasSymbolAtCoordinates(char symbol, PairOfCoordinates first) {
        final Character symbolAtCoordinates = currentState[first.getRow()][first.getColumn()];
        return symbolAtCoordinates != null && symbolAtCoordinates.equals(symbol);
    }

    Character[][] getCurrentState() {
        return deepCopy(currentState);
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

    int getRowsNumber() {
        return rowsNumber;
    }

    int getColumnNumber() {
        return columnNumber;
    }

}
