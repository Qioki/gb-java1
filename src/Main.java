import java.io.*;
import java.util.Scanner;

public class Main {
/*
Создать 2 текстовых файла, примерно по 50-100 символов в каждом(особого значения не имеет);
Написать программу, «склеивающую» эти файлы, то есть вначале идет текст из первого файла, потом текст из второго.
* Написать программу, которая проверяет присутствует ли указанное пользователем слово в файле (работаем только с латиницей).
** Написать метод, проверяющий, есть ли указанное слово в папке (Почитайте реализацию класса File)
 */

    public static void main(String[] args) {
        try {
            //printToFile("file1", "Lorem ipsum nec dui nunc mattis enim ut tellus elementum sagittis vitae et leo duis ut");
            //printToFile("file2", "gravida quis blandit turpis cursus in hac habitasse platea dictumst quisque sagittis purus sit amet");

            //mergeTextFiles(new String[]{"file1", "file2"}, "resultFile");

            String file = findWordInDir("./testf1", "buuuuu");
            System.out.println( !file.equals("-1") ? ("Found in file: " + file) : "Word not found" );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void printToFile(String fileName, String text) throws FileNotFoundException {
        PrintStream ps = new PrintStream(new FileOutputStream(fileName, true));
        ps.println(text);
        ps.close();
    }
    public static void mergeTextFiles(String[] srcFileNames, String toFileName) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        for(String name : srcFileNames) {
            Scanner sc = new Scanner(new FileInputStream(name));
            while (sc.hasNext()) {
                sb.append(sc.nextLine() + "\n");
            }
        }

        printToFile(toFileName, sb.toString());
    }

    public static String findWordInDir(String path, String word) throws FileNotFoundException {

        File file = new File(path);

        if(file.isDirectory()) {
            String[] paths = file.list();
            for(String p:paths) {
                String result = findWordInDir(path + "/" + p, word);
                if (!result.equals("-1"))
                    return result;
            }
        }
        else if(file.isFile()){
            if(findWordInFile(path, word))
                return path;
        }
        return "-1";
    }
    public static boolean findWordInFile(String fileName, String word) throws FileNotFoundException {

        Scanner sc = new Scanner(new FileInputStream(fileName));
        while (sc.hasNext()) {
            if(sc.nextLine().contains(word))
                return true;
        }
        return false;
    }

}
