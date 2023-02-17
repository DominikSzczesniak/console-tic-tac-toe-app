package pl.szczesniak.dominik.tictactoe.Application;

public class FieldNumberTranslator {

    public FieldCoordinates toCoordinates(int number) {
        FieldCoordinates coordinates;
        int row = (number - 1) / 3;
        int column = (number - 1) % 3;

        return coordinates = new FieldCoordinates(row, column);
    }
}
