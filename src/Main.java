import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

    static final int DIMENSION = 3;
    static final char EMPTY_SPACE = '_';
    static final char X_PLAYER = 'X';
    static final char O_PLAYER = 'O';

    public static void main(String[] args) {

        System.out.println("X" + " O " + "X");
        System.out.println("O" + " X " + "O");
        System.out.println("X" + " O " + "X");

        Scanner scanner = new Scanner(System.in);

        char[][] field = new char[DIMENSION][DIMENSION];

        createEmptyField(field);
        printField(field);

        boolean xEnter = false;
        gameLoop:
        while (true) {
            System.out.println("Enter coordinates");
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            if (x < 1 || x > DIMENSION || y < 1 || y > DIMENSION) {
                System.out.println("Coordinates should be from 1 to " + DIMENSION);
            } else {
                if (field[x - 1][y - 1] != EMPTY_SPACE) {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    int valid = 1;
                    while (valid == 1) {
                        if (xEnter == true) {
                            field[x - 1][y - 1] = O_PLAYER;
                            xEnter = false;
                            break;
                        } else {
                            field[x - 1][y - 1] = X_PLAYER;
                            xEnter = true;
                            break;
                        }
                    }
                }

                printField(field);

                if (checkForEndOfGame(field)) {
                    break gameLoop;
                }
            }
        }
    }

    private static boolean checkForEndOfGame(char[][] field) {
        int xs = 0;
        int os = 0;

        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                if (field[i][j] == X_PLAYER) {
                    xs++;
                }
                if (field[i][j] == O_PLAYER) {
                    os++;
                }

                boolean xxx = isWinningStrike(X_PLAYER, field, i, j);
                boolean ooo = isWinningStrike(O_PLAYER, field, i, j);

                if (xxx) {
                    System.out.println("X wins");
                    return true;
                } else if (ooo) {
                    System.out.println("O wins");
                    return true;
                }
            }
        }

        if (xs + os == DIMENSION * DIMENSION) {
            System.out.println("Draw");
            return true;
        } else {
            System.out.println("Game not finished");
            return false;
        }
    }

    private static boolean isWinningStrike(char xPlayer, char[][] field, int i, int j) {
        if (field[i][j] == xPlayer && j+2 < DIMENSION && field[i][j+1] == xPlayer && field[i][j+2] == xPlayer) {
            return true;
        }
        if (field[i][j] == xPlayer && i+2 < DIMENSION && field[i+1][j] == xPlayer && field[i+2][j] == xPlayer) {
            return true;
        }
        if (field[i][j] == xPlayer && i+2 < DIMENSION && j+2 < DIMENSION && field[i+1][j+1] == xPlayer && field[i+2][j+2] == xPlayer) {
            return true;
        }
        if (field[i][j] == xPlayer && i+2 < DIMENSION && j-2 >= 0 && field[i+1][j-1] == xPlayer && field[i+2][j-2] == xPlayer) {
            return true;
        }
        return false;
    }

    private static void createEmptyField(char[][] field) {
        for (int i = 0; i < DIMENSION; ++i) {
            for (int j = 0; j < DIMENSION; ++j) {
                field[i][j] = EMPTY_SPACE;
            }
        }
    }

    private static void printField(char[][] field) {

        System.out.println("--".repeat(DIMENSION + 2));
        for (int i = 0; i < DIMENSION; ++i) {
            System.out.print("| ");
            for (int j = 0; j < DIMENSION; ++j) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("--".repeat(DIMENSION + 2));
    }
}