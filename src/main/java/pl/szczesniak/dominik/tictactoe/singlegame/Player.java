package pl.szczesniak.dominik.tictactoe.singlegame;

public class Player {

    private final Symbol symbol;

    public Player(final Symbol symbol) {
        this.symbol = symbol;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return "Player{" +
                "symbol=" + symbol +
                '}';
    }
}
