package pl.szczesniak.dominik.tictactoe.consoletictactoeapp.gamehistory.domain;


import pl.szczesniak.dominik.tictactoe.consoletictactoeapp.player.model.PlayerScore;
import pl.szczesniak.dominik.tictactoe.core.singlegame.domain.model.PlayerName;

public interface GameHistoryStorage {

	void store(SingleGameResult singleGameResult);

	PlayerScore loadPlayerScore(PlayerName playerName);

}
