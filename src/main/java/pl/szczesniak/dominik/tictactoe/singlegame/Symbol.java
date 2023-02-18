package pl.szczesniak.dominik.tictactoe.singlegame;

import java.util.Objects;

public class Symbol {
    private final char value;

    public Symbol(final char symbol) {
        this.value = symbol;
    }

    public char getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Symbol symbol1 = (Symbol) o;
        return value == symbol1.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "" + value;
    }
}
