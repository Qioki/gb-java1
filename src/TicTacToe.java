import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = 'O';
    private static final char DOT_EMPTY = '.';
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Random RANDOM = new Random();
    private static int fieldSizeX;
    private static int fieldSizeY;
    private static int winnerLineLength;
    private static char[][] field;
    private static char[][] thinking;

    static final int[] vector10 = new int[]{ 1, 0 };
    static final int[] vector11 = new int[]{ 1, 1 };
    static final int[] vector01 = new int[]{ 0, 1 };
    static final int[] vectorM11 = new int[]{ -1, 1 };
    static final int[][] vectors = new int[][]{ vector10, vector11, vector01, vectorM11 };

    public static void main(String[] args) {

        int size = 3;
        winnerLineLength = 3;

        initField(size);
        field[0][0] = DOT_HUMAN;
        field[1][1] = DOT_HUMAN;
        //field[0][2] = DOT_HUMAN;
        printField();

        while (true) {
            humanTurn();
            printField();
            if (checkWin(DOT_HUMAN)) {
                System.out.println("Human win!");
                break;
            }
            if (isDraw()) {
                System.out.println("Draw!");
                break;
            }
            aiTurn();
            printField();
            if (checkWin(DOT_AI)) {
                System.out.println("Computer win!");
                break;
            }
            if (isDraw()) {
                System.out.println("Draw!");
                break;
            }
        }
    }
    private static boolean checkWin(char c) {
        return checkField(c, winnerLineLength).length != 0;
    }
    private static int[] checkField(char c, int lineLength) {

        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if(field[j][i] == c) {
                    int[] coo = checkArea(c, i, j, lineLength);
                    if (coo[4] == 1) {
                        return coo;
                    }
                }
            }
        }
        return new int[0];
    }
    private static int[] checkArea(char c, int x, int y, int lineLength) {
        int minLength = 999;
        int bestLineId = 0;
        for (int i = 0; i < vectors.length; i++) {

            int length = checkLine(c, x, y, vectors[i], lineLength);

            if(minLength > length) {
                minLength = length;
                bestLineId = i;
            }
        }
        System.out.println(Arrays.toString(vectors[bestLineId]));
        return  new int[]{ x, y, vectors[bestLineId][0], vectors[bestLineId][1], lineLength };

        /*return  checkLine(c, x, y, vector10, lineLength) == 1 ||
                checkLine(c, x, y, vector11, lineLength) == 1 ||
                checkLine(c, x, y, vector01, lineLength) == 1 ||
                checkLine(c, x, y, vectorM11, lineLength) == 1;*/
    }
    private static int checkLine(char c, int x, int y, int[] vector, int length) {
        if(x >= fieldSizeX || y >= fieldSizeY || x < 0 || y < 0 || field[y][x] != c) return length;
        return length > 1 ? checkLine(c, x+vector[0], y+vector[1], vector, length-1) : 1;
    }

    private static void aiChecksHuman() {

    }

    private static void aiTurn() {
        int x;
        int y;


        do {
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        } while (!isCellEmpty(x, y));


        field[y][x] = DOT_AI;
    }

    private static void humanTurn() {
        int x;
        int y;
        do {
            System.out.print("Enter coordinates (x y) 1 to 3 >> ");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        } while (!isCellValid(x, y) || !isCellEmpty(x, y));
        field[y][x] = DOT_HUMAN;
    }

    private static boolean isCellValid(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    private static boolean isCellEmpty(int x, int y) {
        return field[y][x] == DOT_EMPTY;
    }





    private static void initField(int size) {
        fieldSizeX = fieldSizeY = size;
        field = new char[fieldSizeY][fieldSizeX];
        //thinking = new char[fieldSizeY][fieldSizeX];
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                field[i][j] = DOT_EMPTY;
            }
        }
    }
    private static void printField() {
        for (int i = 0; i < fieldSizeY; i++) {
            System.out.print("|");
            for (int j = 0; j < fieldSizeX; j++) {
                System.out.print(field[i][j] + "|");
            }
            System.out.println();
        }
    }




    private static boolean isDraw() {
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[y][x] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }


}
