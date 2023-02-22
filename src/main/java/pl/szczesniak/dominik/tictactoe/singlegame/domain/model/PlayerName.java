package pl.szczesniak.dominik.tictactoe.singlegame.domain.model;

public class PlayerName {

	private final String name;

	public PlayerName(final String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return name;
	}
}
