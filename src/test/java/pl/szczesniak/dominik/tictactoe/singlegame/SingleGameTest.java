package pl.szczesniak.dominik.tictactoe.singlegame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.szczesniak.dominik.tictactoe.exceptions.OtherPlayerTurnException;
import pl.szczesniak.dominik.tictactoe.exceptions.PlayerIsNotThePartOfTheGameException;
import pl.szczesniak.dominik.tictactoe.exceptions.SpotAlreadyTakenOnBoardException;

import static org.assertj.core.api.Assertions.*;

class SingleGameTest {

    private static final Symbol SYMBOL_O = new Symbol('O');
    private static final Symbol SYMBOL_X = new Symbol('X');

    private Player playerOne;
    private Player playerTwo;

    @BeforeEach
    void setUp() {
        playerOne = new Player(SYMBOL_O);
        playerTwo = new Player(SYMBOL_X);
    }

    @Test
    void board_should_be_empty_at_the_start_of_the_game() {
        // given
        SingleGame tut = new SingleGame(playerOne, playerTwo);

        // when
        final Character[][] boardView = tut.getBoardView();

        // then
        assertThat(boardView)
                .hasDimensions(3, 3)
                .isEqualTo(new Character[3][3]);
    }

    @Test
    void player_one_should_make_first_move() {
        // given
        SingleGame tut = new SingleGame(playerOne, playerTwo);
        final PlayerMove move = new PlayerMove(0, 0);

        // when
        tut.makeMove(playerOne, move);

        // then
        final Character[][] boardView = tut.getBoardView();
        assertThat(boardView[0]).containsExactly('O', null, null);
        assertThat(boardView[1]).containsExactly(null, null, null);
        assertThat(boardView[2]).containsExactly(null, null, null);
    }

    @Test
    void every_player_should_make_single_move_when_it_is_their_turn() {
        // given
        SingleGame tut = new SingleGame(playerOne, playerTwo);

        // when
        tut.makeMove(playerOne, new PlayerMove(0, 2));
        tut.makeMove(playerTwo, new PlayerMove(1, 2));

        // then
        final Character[][] boardView = tut.getBoardView();
        assertThat(boardView[0]).containsExactly(null, null, 'O');
        assertThat(boardView[1]).containsExactly(null, null, 'X');
        assertThat(boardView[2]).containsExactly(null, null, null);
    }

    @Test
    void same_player_cant_make_many_moves_in_a_row() {
        // given
        SingleGame tut = new SingleGame(playerOne, playerTwo);

        // when
        tut.makeMove(playerOne, new PlayerMove(0, 2));
        Throwable thrown = catchThrowable(() -> tut.makeMove(playerOne, new PlayerMove(1, 2)));

        // then
        assertThat(thrown).isInstanceOf(OtherPlayerTurnException.class);

        final Character[][] boardView = tut.getBoardView();
        assertThat(boardView[0]).containsExactly(null, null, 'O');
        assertThat(boardView[1]).containsExactly(null, null, null);
        assertThat(boardView[2]).containsExactly(null, null, null);

    }

    @Test
    void should_not_make_a_move_on_already_taken_spot() {
        // given
        SingleGame tut = new SingleGame(playerOne, playerTwo);

        // when
        tut.makeMove(playerOne, new PlayerMove(1, 1));
        final Throwable thrown = catchThrowable(() -> tut.makeMove(playerTwo, new PlayerMove(1, 1)));

        // then
        final Character[][] boardView = tut.getBoardView();
        assertThat(thrown).isInstanceOf(SpotAlreadyTakenOnBoardException.class);
        assertThat(boardView[0]).containsExactly(null, null, null);
        assertThat(boardView[1]).containsExactly(null, 'O', null);
        assertThat(boardView[2]).containsExactly(null, null, null);
    }

    @Test
    void player_one_should_not_lose_turn_if_previously_chose_illegal_spot_to_place_symbol_on() {
        // given
        SingleGame tut = new SingleGame(playerOne, playerTwo);

        // when
        tut.makeMove(playerOne, new PlayerMove(1, 1));
        tut.makeMove(playerTwo, new PlayerMove(2, 1));
        final Throwable thrown = catchThrowable(() -> tut.makeMove(playerOne, new PlayerMove(2, 1)));
        tut.makeMove(playerOne, new PlayerMove(0, 0));

        // then
        final Character[][] boardView = tut.getBoardView();
        assertThat(thrown).isInstanceOf(SpotAlreadyTakenOnBoardException.class);
        assertThat(boardView[0]).containsExactly('O', null, null);
        assertThat(boardView[1]).containsExactly(null, 'O', null);
        assertThat(boardView[2]).containsExactly(null, 'X', null);
    }

    @Test
    void player_that_is_not_a_part_in_the_game_shouldnt_be_able_to_make_a_move() {
        // given
        SingleGame tut = new SingleGame(playerOne, playerTwo);
        final Player player = new Player(SYMBOL_X);

        // when
        final Throwable thrown = catchThrowable(() -> tut.makeMove(player, new PlayerMove(1, 1)));

        // then
        assertThat(thrown).isInstanceOf(PlayerIsNotThePartOfTheGameException.class);
        assertThat(tut.getBoardView()).isEqualTo(new Character[3][3]);
    }

    @Test
    void should_not_modify_board_by_modifying_boards_view() {
        // given
        SingleGame tut = new SingleGame(playerOne, playerTwo);
        final Character[][] boardViewBefore = tut.getBoardView();

        // when
        boardViewBefore[0][0] = 'O';
        boardViewBefore[0][1] = 'O';

        // then
        final Character[][] boardViewAfter = tut.getBoardView();
        assertThat(boardViewAfter[0]).containsExactly(null, null, null);
        assertThat(boardViewAfter[1]).containsExactly(null, null, null);
        assertThat(boardViewAfter[2]).containsExactly(null, null, null);

        assertThat(boardViewBefore[0]).containsExactly('O', 'O', null);
    }

    @Test
    void game_should_be_won_by_player_one() {
        // given
        SingleGame tut = new SingleGame(playerOne, playerTwo);

        // when
        tut.makeMove(playerOne, new PlayerMove(0, 0));
        tut.makeMove(playerTwo, new PlayerMove(1, 1));
        tut.makeMove(playerOne, new PlayerMove(0, 1));
        tut.makeMove(playerTwo, new PlayerMove(2, 2));
        tut.makeMove(playerOne, new PlayerMove(0, 2));

        // then
        assertThat(tut.checkIfPlayerWon(playerOne)).isEqualTo(true);
    }

    @Test
    void game_should_be_won_by_player_two() {
        // given
        SingleGame tut = new SingleGame(playerOne, playerTwo);

        // when
        tut.makeMove(playerOne, new PlayerMove(0, 0));
        tut.makeMove(playerTwo, new PlayerMove(1, 1));
        tut.makeMove(playerOne, new PlayerMove(0, 1));
        tut.makeMove(playerTwo, new PlayerMove(1, 2));
        tut.makeMove(playerOne, new PlayerMove(2, 1));
        tut.makeMove(playerTwo, new PlayerMove(1, 0));

        // then
        assertThat(tut.checkIfPlayerWon(playerTwo)).isEqualTo(true);
    }

    @Test
    void game_shouldnt_be_a_draw_if_board_isnt_filled() {
        // given
        SingleGame tut = new SingleGame(playerOne, playerTwo);

        // when
        tut.makeMove(playerOne, new PlayerMove(1, 0));
        tut.makeMove(playerTwo, new PlayerMove(1, 1));
        tut.makeMove(playerOne, new PlayerMove(0, 1));
        tut.makeMove(playerTwo, new PlayerMove(1, 2));

        // then
        assertThat(tut.isDraw(playerOne, playerTwo)).isEqualTo(false);
    }

    @Test
    void game_should_be_a_draw_if_board_is_filled_and_no_winner() {
        // given
        SingleGame tut = new SingleGame(playerOne, playerTwo);

        // when
        tut.makeMove(playerOne, new PlayerMove(0, 0));
        tut.makeMove(playerTwo, new PlayerMove(0, 1));
        tut.makeMove(playerOne, new PlayerMove(1, 0));
        tut.makeMove(playerTwo, new PlayerMove(2, 0));
        tut.makeMove(playerOne, new PlayerMove(1, 1));
        tut.makeMove(playerTwo, new PlayerMove(2, 2));
        tut.makeMove(playerOne, new PlayerMove(2, 1));
        tut.makeMove(playerTwo, new PlayerMove(1, 2));
        tut.makeMove(playerOne, new PlayerMove(0, 2));

        // then
        assertThat(tut.isDraw(playerOne, playerTwo)).isEqualTo(true);
    }

}