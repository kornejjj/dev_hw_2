import java.util.Random;
import java.util.Scanner;

class Game {
    private static final byte BOARD_SIZE = 9;
    private static final char PLAYER = 'X';
    private static final char COMPUTER = 'O';
    private final char[] box = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private boolean boxEmpty = false;
    private byte winner = 0;

    public void start() {
        try (Scanner scan = new Scanner(System.in)) {
            System.out.println("Enter box number to select. Enjoy!\n");

            do {
                printBoard();

                if (!boxEmpty) {
                    for (byte i = 0; i < BOARD_SIZE; i++)
                        box[i] = ' ';
                    boxEmpty = true;
                }

                if (checkWinner()) break;

                // Хід гравця
                playerMove(scan);

                if (checkWinCondition(PLAYER)) {
                    winner = 1;
                    continue;
                }

                if (isBoardFull()) {
                    winner = 3;
                    continue;
                }

                // Хід комп'ютера
                computerMove();

                if (checkWinCondition(COMPUTER)) {
                    winner = 2;
                }
            } while (true);
        }
    }

    private void printBoard() {
        System.out.println("\n\n " + box[0] + " | " + box[1] + " | " + box[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + box[3] + " | " + box[4] + " | " + box[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + box[6] + " | " + box[7] + " | " + box[8] + " \n");
    }

    private boolean checkWinner() {
        if (winner == 1) {
            System.out.println("You won the game!\nCreated by Shreyas Saha. Thanks for playing!");
            return true;
        } else if (winner == 2) {
            System.out.println("You lost the game!\nCreated by Shreyas Saha. Thanks for playing!");
            return true;
        } else if (winner == 3) {
            System.out.println("It's a draw!\nCreated by Shreyas Saha. Thanks for playing!");
            return true;
        }
        return false;
    }

    private void playerMove(Scanner scan) {
        byte input;
        while (true) {
            input = scan.nextByte();
            if (input > 0 && input <= BOARD_SIZE) {
                if (box[input - 1] == PLAYER || box[input - 1] == COMPUTER) {
                    System.out.println("That one is already in use. Enter another.");
                } else {
                    box[input - 1] = PLAYER;
                    break;
                }
            } else {
                System.out.println("Invalid input. Enter again.");
            }
        }
    }

    private boolean checkWinCondition(char player) {
        return (box[0] == player && box[1] == player && box[2] == player) ||
                (box[3] == player && box[4] == player && box[5] == player) ||
                (box[6] == player && box[7] == player && box[8] == player) ||
                (box[0] == player && box[3] == player && box[6] == player) ||
                (box[1] == player && box[4] == player && box[7] == player) ||
                (box[2] == player && box[5] == player && box[8] == player) ||
                (box[0] == player && box[4] == player && box[8] == player) ||
                (box[2] == player && box[4] == player && box[6] == player);
    }

    private boolean isBoardFull() {
        for (char c : box) {
            if (c != PLAYER && c != COMPUTER) {
                return false;
            }
        }
        return true;
    }

    private void computerMove() {
        Random rand = new Random();
        int randPos;
        while (true) {
            randPos = rand.nextInt(BOARD_SIZE);
            if (box[randPos] != PLAYER && box[randPos] != COMPUTER) {
                box[randPos] = COMPUTER;
                break;
            }
        }
    }
}
