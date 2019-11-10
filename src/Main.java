import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Objects;
/*
Написать программу, которая будет измерять размер всего содержимого папки, путь которой передаётся на вход,
и выводить его в удобочитаемом виде — в байтах, килобайтах, мегабайтах или гигабайтах.
 */
public class Main {
    private static long length;
    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (;;) {
            try {
                System.out.println("Введите путь к файлу или к папке:");
                File file = new File(reader.readLine().trim());
                if (file.exists()) {
                    fileSize(file);
                } else {
                    System.out.println("Неверный путь или имя файла!");
                    continue;
                }
                print();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private static void fileSize(File file) {
        if (file.isDirectory()) {
            Arrays.stream(Objects.requireNonNull(file.listFiles())).forEach(Main::fileSize);
        } else {
            length += file.length();
        }
    }

    public static void print() {
        DecimalFormat dformate = new DecimalFormat("#.#");
        if (length > 1073741824) {
            System.out.println("Размер : " + dformate.format((double) length / 1073741824) + " Гбайт\n\tили "
                    + dformate.format((double) length / 1048576) + " МБайт\n\tили "
                    + dformate.format((double) length / 1024) + " Кбайт");
        } else if (length > 1048576) {
            System.out.println("Размер : " + dformate.format((double) length / 1048576) + " МБайт\n\tили "
                    + dformate.format((double) length / 1024) + " Кбайт");
        } else if (length > 1024) {
            System.out.println("Размер файла: " + dformate.format((double) length / 1024) + " Кбайт");
        }
            System.out.println("Общий размер файла: " + dformate.format((double) length) + " байт");
    }
}
