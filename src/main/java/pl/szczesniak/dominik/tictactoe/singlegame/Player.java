package pl.szczesniak.dominik.tictactoe.singlegame;

import pl.szczesniak.dominik.tictactoe.exceptions.SymbolIsUnsupportedException;
import java.util.Set;

public class Player {

    private final Symbol symbol;

    public Player(final Symbol symbol) {
        final Set<Symbol> supportedSymbols = Set.of(new Symbol('X'), new Symbol('O'));
        if (supportedSymbols.stream().noneMatch(marker -> marker.getValue() == symbol.getValue())) {
            throw new SymbolIsUnsupportedException("Symbol " + symbol + " is unsupported.");
        }
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
