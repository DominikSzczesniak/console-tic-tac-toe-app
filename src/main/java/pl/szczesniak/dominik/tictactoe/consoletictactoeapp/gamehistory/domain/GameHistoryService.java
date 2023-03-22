package pl.szczesniak.dominik.tictactoe.consoletictactoeapp.gamehistory.domain;


import pl.szczesniak.dominik.tictactoe.consoletictactoeapp.player.model.PlayerScore;
import pl.szczesniak.dominik.tictactoe.core.singlegame.domain.model.PlayerName;

public class GameHistoryService {

	private final GameHistoryStorage storage;

	public GameHistoryService(final GameHistoryStorage storage) {
		this.storage = storage;
	}

	public void store(SingleGameResult singleGameResult) {
		storage.store(singleGameResult);
	}
	
	public PlayerScore loadPlayerScore(final PlayerName playerName) {
		return storage.loadPlayerScore(playerName);
	}

}
