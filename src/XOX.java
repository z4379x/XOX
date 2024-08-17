import java.util.Scanner;
import java.util.Random;


public class XOX {

    private static char[][] board = new char[3][3];
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        init();
        newBoard();
        
        while (true) {
            if (currentPlayer == 'X') {
                pMove();
            } else {
                cMove();
            }
            newBoard();
            if (checkWin()) {
                if (currentPlayer == 'X') {
                    System.out.println("you win.");
                }
                else {
                    System.out.println("i win.");
                }

                break;
            }
            if (fullBoard()) {
                System.out.println("its a tie.");
                break;
            }
            switchPlayer();
        }
    }

    private static void init() {
        int counter = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = (char) ('0' + counter++);
            }
        }
    }

    private static void newBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void pMove() {
        Scanner scanner = new Scanner(System.in);
        int move;

        while (true) {
            System.out.println("\nplayer " + currentPlayer + ", enter your move: ");
            move = scanner.nextInt();

            if (move >= 1 && move <= 9 && validMove(move)) {
                System.out.println("\nnew board:");
                placeMove(move, currentPlayer);

                break;
            } else {
                System.out.println("this move isnt valid.");
            }
        }
    }

    private static void cMove() {
        Random rand = new Random();
        int move;

        while (true) {
            move = rand.nextInt(9) + 1;
            if (validMove(move)) {
                try {
                    Thread.sleep(1000); //cok hizli hamle yapinca kafa karisiyo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("\ni play: " + move);
                System.out.println("\nnew board:");
                placeMove(move, currentPlayer);
                break;
            }
        }
    }

    private static boolean validMove(int move) {
        int row = (move - 1) / 3;
        int col = (move - 1) % 3;
        return board[row][col] != 'X' && board[row][col] != 'O';
    }

    private static void placeMove(int move, char player) {
        int row = (move - 1) / 3;
        int col = (move - 1) % 3;
        board[row][col] = player;
    }

    private static boolean checkWin() {
        // duz
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;
            }
        }
        //capraz
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true;
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true;
        }
        return false;
    }

    private static boolean fullBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != 'X' && board[i][j] != 'O') {
                    return false;
                }
            }
        }
        return true;
    }

    private static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }
}

