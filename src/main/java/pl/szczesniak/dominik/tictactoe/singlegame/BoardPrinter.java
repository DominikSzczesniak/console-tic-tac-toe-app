package pl.szczesniak.dominik.tictactoe.singlegame;

public class BoardPrinter {

    Character[][] boardPrint = {{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}};;

//    -------------------------------------------------------------         -------------------------------
//    |                   |                   |                   |         |         |         |         |
//    |    board[0][0]    |    board[0][1]    |    board[0][2]    |         |    1    |    2    |    3    |
//    |                   |                   |                   |         |         |         |         |
//    -------------------------------------------------------------         -------------------------------
//    |                   |                   |                   |         |         |         |         |
//    |    board[1][0]    |    board[1][1]    |    board[1][2]    |         |    4    |    5    |    6    |
//    |                   |                   |                   |         |         |         |         |
//    -------------------------------------------------------------         -------------------------------
//    |                   |                   |                   |         |         |         |         |
//    |    board[2][0]    |    board[2][1]    |    board[2][2]    |         |    7    |    8    |    9    |
//    |                   |                   |                   |         |         |         |         |
//    -------------------------------------------------------------         -------------------------------


    public void printBoard(Character[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != null) {
                    boardPrint[i][j] = board[i][j];
                }
            }
        }
        System.out.println("" +
                "-------------------------------\n" +
                "|         |         |         |\n" +
                "|    " + boardPrint[0][0] + "    |    " + boardPrint[0][1] + "    |    " + boardPrint[0][2] + "    |\n" +
                "|         |         |         |\n" +
                "-------------------------------\n" +
                "|         |         |         |\n" +
                "|    " + boardPrint[1][0] + "    |    " + boardPrint[1][1] + "    |    " + boardPrint[1][2] + "    |\n" +
                "|         |         |         |\n" +
                "-------------------------------\n" +
                "|         |         |         |\n" +
                "|    " + boardPrint[2][0] + "    |    " + boardPrint[2][1] + "    |    " + boardPrint[2][2] + "    |\n" +
                "|         |         |         |\n" +
                "-------------------------------");
    }

}
