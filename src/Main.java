public class Main {


    public static void main(String[] args) {

        System.out.println(getFloat(2, 3, 4, 5));

        System.out.println(checkSum(12, 13));

        System.out.println(checkSign(-1));

        System.out.println(helloName("Андрей"));

        System.out.println(checkYear(1004));

    }

    static float getFloat(int a, int b, int c, int d) {
        return a * (b + ((float) c / d));
    }

    static boolean checkSum(int a, int b) {
        int sum = a + b;
        return sum <= 20 && sum >= 10;
    }

    static String checkSign(int a) {
        return a >= 0 ? "Положительное число" : "Отрицательное число";
    }

    static String helloName(String name) {
        return "Привет, " + name;
    }

    static String checkYear(int year) {

        return year % 400 == 0 || ( year % 100 != 0 && year % 4 == 0 ) ? "Високосный" : "Невисокосный";
    }
}
