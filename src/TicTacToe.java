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

    static final int[] vector10 = new int[]{ 1, 0 };
    static final int[] vector11 = new int[]{ 1, 1 };
    static final int[] vector01 = new int[]{ 0, 1 };
    static final int[] vectorM11 = new int[]{ -1, 1 };
    static final int[][] vectors = new int[][]{ vector10, vector11, vector01, vectorM11 };

    public static void main(String[] args) {

        int size = 3;
        winnerLineLength = 3;

        initField(size);
        //field[2][0] = DOT_HUMAN;
        //field[1][1] = DOT_HUMAN;
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
        //return checkField(c, winnerLineLength)[3] <= 1;
        int[] result = checkField(c);
        return result.length != 0 && result[2] == 0;
    }

    private static int[] checkField(char c) {
        int[] temp;
        int[] bestChoice = new int[]{0, 0, 999};
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                temp = isPossibleHere(c, j, i, winnerLineLength); 
                if(temp[2] < bestChoice[2])
                    bestChoice = temp; 
            }
        }
        return bestChoice;
    }

    private static int[] isPossibleHere(char c, int x, int y, int lineLength) {
        int[] temp;
        int[] bestChoice = new int[]{0, 0, 999};
        for (int i = 0; i < vectors.length; i++) { 
            temp = isPossibleLine(c, x, y, vectors[i], lineLength); 
            if(temp.length != 0 && temp[2] < bestChoice[2])
                bestChoice = temp;
        }
        //System.out.println(Arrays.toString(bestChoice));
        return bestChoice;
    }

    private static int[] isPossibleLine(char c, int x, int y, int[] vector, int length) {
        if( (x + vector[0]*length > fieldSizeX) || 
            (y + vector[1]*length > fieldSizeY) || 
            (x + vector[0]*length < -1) || 
            (y + vector[1]*length < -1)) 
            return new int[0];

        //System.out.println("@@@");
        int [] emptyHere = new int[3];
        int emptyCount = 0;
        for (int i = 0; i < length; i++) {
            if(field[y][x] == DOT_EMPTY) {
                emptyHere[0] = y;
                emptyHere[1] = x;
                emptyHere[2] = ++emptyCount;
            }
            else if(field[y][x] != c) return new int[0];
            x += vector[0];
            y += vector[1];
        }
        return emptyHere;
    } 

    private static int[] checkField(char c, int lineLength) {
        int[] bestChoice = new int[]{0, 0, 0, 999};
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if(field[j][i] == c) {
                    int[] coo = checkArea(c, i, j, lineLength);
                    if (coo[3] == 1) {
                        return coo;
                    }
                    if(coo[3] < bestChoice[3]) {
                        bestChoice = coo;                
                    }
                }
            }
        }
        return bestChoice;
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
        System.out.println(minLength);
        return  new int[]{ x, y, bestLineId, minLength}; 
    }
    private static int checkLine(char c, int x, int y, int[] vector, int length) {
        if(x >= fieldSizeX || y >= fieldSizeY || x < 0 || y < 0 || field[y][x] != c) return length + 1;
        return length > 1 ? checkLine(c, x+vector[0], y+vector[1], vector, length-1) : 1;
    } 

    private static void aiTurn() {
        int caution = 1;  
        int x = RANDOM.nextInt(fieldSizeX);
        int y = RANDOM.nextInt(fieldSizeY);

        int[] humanState = checkField(DOT_HUMAN);
        if(humanState[2] <= caution) {
            x = humanState[1];
            y = humanState[0];
        }
        int[] aiState = checkField(DOT_HUMAN);
        if(aiState[2] == 1) {
            x = aiState[1];
            y = aiState[0];
        }


        while (!isCellEmpty(x, y)) {
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        }


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
