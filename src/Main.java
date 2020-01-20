import java.util.Arrays;
import java.util.Random;

public class Main {
/*
1 Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
Написать метод, заменяющий в принятом массиве 0 на 1, 1 на 0;
2 Задать пустой целочисленный массив размером 8. Написать метод, который помощью цикла заполнит его значениями 1 4 7 10 13 16 19 22;
3 Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ], написать метод, принимающий на вход массив и умножающий числа меньше 6 на 2;
4 Задать одномерный массив. Написать методы поиска в нём минимального и максимального элемента;
5 * Создать квадратный целочисленный массив (количество строк и столбцов одинаковое),
заполнить его диагональные элементы единицами, используя цикл(ы);
6 ** Написать метод, в который передается не пустой одномерный целочисленный массив,
метод должен вернуть true если в массиве есть место, в котором сумма левой и правой части массива равны.
Примеры: checkBalance([1, 1, 1, || 2, 1]) → true, checkBalance ([2, 1, 1, 2, 1]) → false,
checkBalance ([10, || 1, 2, 3, 4]) → true. Абстрактная граница показана символами ||, эти символы в массив не входят.
7 *** Написать метод, которому на вход подаётся одномерный массив и число n (может быть положительным,
или отрицательным), при этом метод должен циклически сместить все элементы массива на n позиций.
8 **** Не пользоваться вспомогательным массивом при решении задачи 7.
 */

    public static void main(String[] args) {

        // 1
        Random random = new Random();
        int[] arr = getRandomArray(10, 0, 2);

        printArray("1.\n", arr);
        swapValuesInArray(arr, 0, 1);
        printArray("", arr);

        // 2
        arr = new int[8];

        fillArray(arr);
        printArray("2.\n", arr);

        // 3
        arr = new int[]{1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 };
        if6Array(arr);
        printArray("3.\n", arr);

        // 4
        arr = getRandomArray(20, 0, 20);
        printArray("4.\n", arr);
        System.out.println("min: " + findMin(arr));
        System.out.println("max: " + findMax(arr));

        // 5
        int[][] arr2 = new int[10][10];
        drawOnMatrixX(arr2);
        System.out.println("5.");
        for (int i = 0; i < arr2.length; i++) {
            printArray("", arr2[i]);
        }

        // 6
        arr = new int[] { 5, 2, 4, 2, 1 };
        System.out.println("6.\n" + checkBalance(arr));

        // 7
//        int n = 3;
//        arr = getRandomArray(10, 0, 10);


    }


    static boolean checkBalance(int[] arr) {
        int rightSide = 0;
        for (int value : arr) {
            rightSide += value;
        }
        int leftSide = 0;
        for (int value : arr) {
            leftSide += value;
            rightSide -= value;
            if (leftSide == rightSide) return true;
        }
        return false;
    }

    static void drawOnMatrixX(int[][] arr2) {
        for (int i = 0; i < arr2.length; i++) {
            for (int j = 0; j < arr2[i].length; j++) {
                if(i == j || i + j + 1 == arr2.length)
                    arr2[i][j] = 1;
            }
        }
    }

    static int findMin(int[] arr) {
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if(arr[i] < min) min = arr[i];
        }
        return min;
    }
    static int findMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if(arr[i] > max) max = arr[i];
        }
        return max;
    }

    static void if6Array(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] < 6) {
                arr[i] *= 2;
            }
        }
    }

    static void fillArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i * 3 + 1;
        }
    }

    static void swapValuesInArray(int[] array_01, int value_1, int value_2) {
        for (int i = 0; i < array_01.length; i++) {
            //array_01[i] = array_01[i] == 0 ? 1 : 0;
            if(array_01[i] == value_1) array_01[i] = value_2;
            else if(array_01[i] == value_2) array_01[i] = value_1;
        }
    }



    static void printArray(String label, int[] arr) {
        System.out.println(label + Arrays.toString(arr));
    }

    private static int[] getRandomArray(int arrSize, int minValue, int maxValue) {
        Random random = new Random();
        return random.ints(arrSize, minValue, maxValue + 1).toArray();
    }

}
