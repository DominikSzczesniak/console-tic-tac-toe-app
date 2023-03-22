package pl.szczesniak.dominik.tictactoe.consoletictactoeapp.gamehistory.domain;


import pl.szczesniak.dominik.tictactoe.consoletictactoeapp.player.model.PlayerName;
import pl.szczesniak.dominik.tictactoe.consoletictactoeapp.player.model.PlayerScore;

public interface GameHistoryStorage {

	void store(SingleGameResult singleGameResult);

	PlayerScore loadPlayerScore(PlayerName playerName);

}
