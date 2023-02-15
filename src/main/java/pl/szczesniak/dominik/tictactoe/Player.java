package pl.szczesniak.dominik.tictactoe;

public class Player {

    final char symbol;
    public Player(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return "Player{" +
                "symbol=" + symbol +
                '}';
    }
}
