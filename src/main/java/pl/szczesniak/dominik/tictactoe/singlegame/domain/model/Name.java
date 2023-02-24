package pl.szczesniak.dominik.tictactoe.singlegame.domain.model;

public class Name {

	private final String name;

	public Name(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
}
