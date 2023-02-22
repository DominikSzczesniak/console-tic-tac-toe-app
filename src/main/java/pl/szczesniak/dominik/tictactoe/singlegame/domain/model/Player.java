package pl.szczesniak.dominik.tictactoe.singlegame.domain.model;

public class Player {

    private final Symbol symbol;
    private final PlayerName name;

    public Player(final Symbol symbol, final PlayerName name) {
        this.symbol = symbol;
        this.name = name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public PlayerName getName() {
        return name;
    }

}
