package pl.szczesniak.dominik.tictactoe.singlegame;

import org.junit.jupiter.api.Test;
import pl.szczesniak.dominik.tictactoe.exceptions.SymbolIsUnsupportedException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class PlayerTest {

    private Player tut;

    @Test
    void should_not_be_able_to_choose_invalid_symbol() {
        // when
        Throwable thrown = catchThrowable(() -> tut = new Player(new Symbol('A')));

        // then
        assertThat(thrown).isInstanceOf(SymbolIsUnsupportedException.class);
    }

}