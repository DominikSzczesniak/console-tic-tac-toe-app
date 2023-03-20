package pl.szczesniak.dominik.tictactoe.consoletictactoeapp.game.application;

class BoardPrinter {

	private final Character[][] boardPrint;

	BoardPrinter(final int size) {
		boardPrint = new Character[size][size];
		for (int row = 0; row < size; row++) {
			for (int column = 0; column < size; column++) {
				boardPrint[row][column] = ' ';
			}
		}
	}

	void printBoard(final Character[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] != null) {
					boardPrint[i][j] = board[i][j];
				}
			}
		}
		char alphabet = 'A';
		int length = board.length - 1;
		System.out.print("  ");
		for (int number = 1; number <= board.length; number++) {
			System.out.print("     " + number + "    ");
		}
		System.out.println();
		System.out.print("  ");
		for (int i = 0; i <= length; i++) {
			System.out.print(" ---------");
		}
		for (int row = 0; row < board.length; row++) {
			for (int column = 0; column < board.length; column = column + board.length) {
				System.out.println();
				System.out.print("  ");
				for (int i = 0; i <= length; i++) {
					System.out.print("|         ");
				}
				System.out.print("|");
				System.out.println();
				System.out.print(alphabet + " ");
				alphabet++;
				for (int i = 0; i <= length; i++) {
					System.out.print("|    " + boardPrint[row][i] + "    ");
				}
				System.out.print("|");
				System.out.println();
				System.out.print("  ");
				for (int i = 0; i <= length; i++) {
					System.out.print("|         ");
				}
				System.out.print("|");
				System.out.println();
				System.out.print("  ");
				for (int i = 0; i <= length; i++) {
					System.out.print(" ---------");
				}
			}
		}
		System.out.println();
	}
}

