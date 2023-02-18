package pl.szczesniak.dominik.tictactoe.Application;

public class FieldNumberTranslator {

    FieldCoordinates toCoordinates(final int number) {
        int row = (number - 1) / 3;
        int column = (number - 1) % 3;

        return new FieldCoordinates(row, column);
    }
}
